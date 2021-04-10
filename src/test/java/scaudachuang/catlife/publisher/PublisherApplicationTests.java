package scaudachuang.catlife.publisher;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scaudachuang.catlife.publisher.dao.CatMapper;
import scaudachuang.catlife.publisher.dao.UserMapper;

import javax.annotation.Resource;

@SpringBootTest
class PublisherApplicationTests {
    @Resource
    private CatMapper catMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    public void mybatisT() {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(this.getClass().getResourceAsStream("mybatis-config.xml"));
        SqlSession session = sqlSessionFactory.openSession();
        CatMapper mapper = session.getMapper(CatMapper.class);
        System.out.println(mapper.getCat("cl"));
    }
    @Test
    void contextLoads() {
        PageParams pageParams = new PageParams();
        pageParams.setStart(0);
        pageParams.setLimit(2);
        userMapper.getUserFollowedUserInfoBar(1, pageParams).forEach(System.out::println);
    }

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }

    void kMethod() {
        ListNode head = new ListNode(Integer.MAX_VALUE);
        ListNode p = head;
        int k = 3, i = 0;
        while(i < 10) {
            p.next = new ListNode(i);
            p = p.next;
            i ++;
        }
        System.out.println(getNext(head,k));
    }

    int getNext(ListNode p, int k) {
        int layer = 0;
        if (p.next == null) {
            return layer + 1;
        }
        if ((layer += getNext(p.next, k) + 1) == k - 1)
            System.out.println(p.data);
        return layer;
    }
}
