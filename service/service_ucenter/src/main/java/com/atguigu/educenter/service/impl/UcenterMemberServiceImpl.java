package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-01-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
   @Autowired
   private RedisTemplate<String,String> redisTemplate;
   @Autowired
   private UcenterMemberService memberService;
    //登录的方法
    @Override
    public String login(UcenterMember member) {
        //获取登录的手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //手机号和密码的非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException("20001","登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember moblieMember = baseMapper.selectOne(wrapper);
        if (moblieMember == null){
            throw new GuliException("20001","登录失败");
        }
        //判断密码
        if(!MD5.encrypt(password).equals(moblieMember.getPassword())){
            throw new GuliException("20001","登录失败");
        }
        //判断用户是否被禁用
        if(moblieMember.getDisabled()){
            throw new GuliException("20001","登录失败");
        }
        //登录成功
        //按照规则生成token字符串
        String jwtToken = JwtUtils.getJwtToken(moblieMember.getId(), moblieMember.getNickname());
        return jwtToken;
    }
    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                ||StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)
        ){
            throw new GuliException("20001","注册失败");
        }
        //验证码判断
        //获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new GuliException("20001","注册失败");
        }
        //判断手机号是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new GuliException("20001","注册失败");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setAvatar("https://19046049.oss-cn-hangzhou.aliyuncs.com/2023/01/04/1672810270443file.png");
        baseMapper.insert(ucenterMember);
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
//    //根据token获取用户信息
//    @GetMapping("getMemberInfo")
//    public R getMemberInfo(HttpServletRequest request){
//        String memberId = JwtUtils.getMemberIdByJwtToken(request);
//        UcenterMember member = memberService.getById(memberId);
//        return R.ok().data("userInfo",member);
//    }
}
