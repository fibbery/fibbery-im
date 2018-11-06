package com.fibbery.im.utils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;

/**
 * 处理一些用户信息
 *
 * @author fibbery
 * @date 2018/11/5
 */
public class UserUtils {

    private static final Map<Long, String> userMap = Maps.asMap(
            Sets.newHashSet(11111111L, 22222222L),
            memberId -> "会员" + memberId
    );


    public static String getUserName(Long userId) {
        return userMap.get(userId);
    }

}
