package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-01-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //登录
    String login(UcenterMember member);

    //注册
    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);
}
