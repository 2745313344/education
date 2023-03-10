package com.atguigu.smsservice.Utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GetRandomNumber {
    public String Number(){
        String code = "";
        Random random =new Random();
        int nums [] ={-1,-1,-1,-1,-1,-1};
        for (int i = 0; i < nums.length; i++) {
            int n = random.nextInt(100) / 10;
            nums [i] = n;
            for(int j=0;j < nums.length; j++) {
                if (nums[j] == n && i!=j) {
                    nums[j] = -1;
                    i--;
                    break;
                }
                if(j>= nums.length -1){
                    code += n;
                }
            }
        }
        return code;
    }
}
