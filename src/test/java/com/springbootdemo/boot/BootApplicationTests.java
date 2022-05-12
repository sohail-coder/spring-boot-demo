package com.springbootdemo.boot;

import com.springbootdemo.boot.dao.ProductRepository;
import com.springbootdemo.boot.dao.UserRepository;
import com.springbootdemo.boot.dto.ProductsDTO;
import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.service.CustomerService;
import com.springbootdemo.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class BootApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private UserService userService;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ProductRepository productRepository;

	@Test
	void testAddProduct(){
		Products product = new Products("abcd","123");
		when(userService.addProduct(product)).thenReturn(product);
	}
	@Test
	void testFindProduct(){
		Products product = new Products("abcd","123");
		product.setId(5);
		ProductsDTO productsDTO = new ProductsDTO();
		productsDTO.setId(5);
		productsDTO.setName("abcd");
		productsDTO.setPrice("1234");
		when(userService.findProduct(5)).thenReturn(productsDTO);
	}
	@Test
	void testDeleteProduct() {
		Products product = new Products("abcd","123");
		product.setId(5);
		when(userService.deleteProduct(5)).thenReturn(product);
	}

	@Test
	void testFindUser(){
		User user = new User(
				"abc","xyz","abc@gmail.com","123","{noop}test"
		);
		userRepository.save(user);
		when(userService.findUser("abc@gmail.com")).thenReturn(user);
	}


//	@Test
//	void testDeleteProductCustomer(){
//		User user = new User();
//		Products product = new Products();
//		product.setId(3);
//		product.setName("abcd");
//		product.setPrice("123");
//		userService.addProduct(product);
////		productRepository.save(product);
//		System.out.println(productRepository.findById(3));
//		user.setId(3);
//		user.setFirstName("abc");
//		user.setLastName("xyz");
//		user.setEmail("abc@gmail.com");
//		user.setPhone("123546");
//		user.setPassword("{noop}test");
//
//		userRepository.save(user);
//
//		UserDTO userDTO = this.convertUertoDto(user);
//		List<Products> products = new ArrayList<>();
//		this.testAddProduct();
//		products.add(customerService.addProduct(userDTO,3));
//		user.setProducts(products);
////		UserDTO userDTO = this.convertUertoDto(userRepository.getById("abc@gmail.com"));
//		userDTO.setProducts(user.getProducts());
//		System.out.println(userDTO);
//
//
//	}


	private UserDTO convertUertoDto(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setProducts(user.getProducts());
		userDTO.setEmail(user.getEmail());
		userDTO.setLastName(user.getLastName());
		userDTO.setPhone(user.getPhone());
		return userDTO;
	}



//	@Test
//	void testAddProducts(){
//		UserDTO userDTO = new UserDTO();
//		User user = new User();
////		userDTO.setId(3);
////		userDTO.setFirstName("abc");
////		userDTO.setLastName("xyz");
////		userDTO.setEmail("abc@gmail.com");
////		userDTO.setPhone("123546");
//
//		user.setId(3);
//		user.setFirstName("abc");
//		user.setLastName("xyz");
//		user.setEmail("abc@gmail.com");
//		user.setPhone("123546");
//		user.setPassword("{noop}test");
//		userRepository.save(user);
//		User user1 = userRepository.getById("abc@gmail.com");
//		System.out.println(user);
//		System.out.println(user1.getId());
//		userDTO.setId(user1.getId());
//		userDTO.setFirstName(user1.getFirstName());
//		userDTO.setLastName(user1.getLastName());
//		userDTO.setEmail(user1.getEmail());
//		userDTO.setPhone(user1.getPhone());
//		userDTO.setProducts(user1.getProducts());
//		when(customerService.addProduct(userDTO,1)).thenReturn(productRepository.getById(1));
//	}


}

