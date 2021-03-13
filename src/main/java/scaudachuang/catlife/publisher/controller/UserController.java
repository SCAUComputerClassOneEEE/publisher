package scaudachuang.catlife.publisher.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标注后的DELETE、POST、GET代表访问资源方式
 * 登录/users/status         POST,GET
 * 关注/users/attentions     POST,GET
 * 粉丝/users/fans           GET
 * 屏蔽/users/blacklist      POST
 * 动态/users/moments        POST,GET
 * 评论/users/comments       POST,GET
 * 备忘录/users/memorandums  POST,GET
 * @author best lu
 * @since 2021/3/10
 */
@RestController
@RequestMapping("/users")
public class UserController {

}
