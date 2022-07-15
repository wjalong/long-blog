package com.wjalong.longuser.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjalong.entity.BlogUser;
import com.wjalong.longuser.user.service.BlogUserService;
import com.wjalong.mapper.BlogUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yixin
 * @since 2022-07-14
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

}
