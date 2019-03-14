package com.chenhao.springboot.springbootmy;

import com.alibaba.fastjson.JSON;
import com.chenhao.springboot.springbootmy.entity.User;
import com.chenhao.springboot.springbootmy.repository.UserReposity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMyApplicationTests {
	@Autowired
	private UserReposity userReposity;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 新增测试
	 */
	@Test
	public void insert() {
		for(int i=0;i<10000;i++){
			User user=new User();
			user.setName("刘诗诗_"+i);
			user.setPwd("123333_"+i);
			user.setJob("超级大美女");
			user.setAge(35);
			user.setHome("香港太平山顶超级豪宅");
            user.setImgUrl("C:\\Users\\lenovo\\Desktop\\123.jpg");
			userReposity.insert(user);
		}

		System.out.println("新增成功");
	}

	/**
	 * 查询全部
	 */
	@Test
	public void findAll(){
		List<User> list=userReposity.findAll();
		if(list==null || list.size()==0){
			System.out.println("查询失败,没有数据");
		}else{
			for(User u:list){
				System.out.println(u.toString());
			}
		}
	}

	/**
	 * 查询单个
	 */
	@Test
	public void findByName(){
		User user=userReposity.findByName("刘诗诗");
		System.out.println(user.toString());
	}

	/**
	 * 查询年龄条件记录
	 */
    @Test
	public void findByAgeGT(){
		List<User> list=userReposity.findByAgeGreaterThan(34);
		if(list==null || list.size()==0){
			System.out.println("查询失败,没有数据");
		}else{
			for(User u:list){
				System.out.println(u.toString());
			}
		}
	}

	/**
	 * 查询年龄服务条件
	 */
    @Test
	public void findUserOther(){
		Query query = new Query(Criteria.where("age").in(35,37));
		List<User> list=mongoTemplate.find(query,User.class);
		if(list==null || list.size()==0){
			System.out.println("查询失败,没有数据");
		}else{
			for(User u:list){
				System.out.println(u.toString());
			}
		}
	}

	/**
	 * 更新操作
	 */
	@Test
	public void update(){
		for(int i=0;i<10000;i++){
			Query query=new Query(Criteria.where("name").is("刘诗诗_"+i));
			Update update=new Update();
			update.addToSet("gender","female");
			update.addToSet("phone","1234_"+i);
			update.addToSet("place","吴奇隆家里");
			update.addToSet("role","模特,歌手，影视明星");
			update.addToSet("status",0);
			//update.addToSet("age",i);
			mongoTemplate.updateFirst(query,update,User.class);
		}
		System.out.println("批量更新ok！");
		Query query2=new Query(Criteria.where("name").in("刘诗诗_0","刘诗诗_200"));
        List<User> list=mongoTemplate.find(query2,User.class);
        if(list!=null){
			System.out.println("共计存在记录::"+list.size());
        	for(User u:list){
				System.out.println(JSON.toJSONString(u));
			}
		}else{
        	System.out.println("没有记录");
		}
	}

	/**
	 * 排序分页
	 */
	@Test
	public void sortAndLimit(){
		Query query=new Query();
		query.skip(5).limit(20);
		List<User> list=mongoTemplate.find(query,User.class);
		if(list!=null){
			list.sort(new Comparator<User>() {
				@Override
				public int compare(User u1, User u2) {
					if(u1.getAge()>u2.getAge()){
                        return 1;
					}else if(u1.getAge()<u2.getAge()){
						return -1;
					}
					return 0;
				}
			});


			System.out.println("共计存在记录::"+list.size());
			for(User u:list){
				System.out.println(JSON.toJSONString(u));
			}
		}else{
			System.out.println("没有记录");
		}

	}

	/**
	 * 删除操作
	 */
	@Test
	public void delete(){
		for(int i=0;i>5;i++){
			User user=new User();
			user.setName("刘诗诗_"+i);
			userReposity.delete(user);
		}
		System.out.println("批量删除ok。。。。");
		System.out.println("-----------");
		String[] names={"刘诗诗_0"};
		Query query=new Query(Criteria.where("name").nin(names));
		mongoTemplate.remove(query,User.class);
		System.out.println("第二种删除方式也0k。。。。。");
	}


}
