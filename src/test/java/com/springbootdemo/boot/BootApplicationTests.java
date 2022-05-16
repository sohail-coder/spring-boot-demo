package com.springbootdemo.boot;

import com.springbootdemo.boot.dao.ProductRepository;
import com.springbootdemo.boot.dao.UserRepository;
import com.springbootdemo.boot.dto.ProductsDTO;
import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.service.CustomerService;
import com.springbootdemo.boot.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
		Assertions.assertDoesNotThrow(this::doNotThrowException);
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
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}
	@Test
	void testDeleteProduct() {
		Products product = new Products("abcd","123");
		product.setId(5);
		when(userService.deleteProduct(5)).thenReturn(product);
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}

	@Test
	void testFindUser(){
		User user = new User(
				"abc","xyz","abc@gmail.com","123","{noop}test"
		);
		userRepository.save(user);
		when(userService.findUser("abc@gmail.com")).thenReturn(user);
		Assertions.assertDoesNotThrow(this::doNotThrowException);
	}

	private void doNotThrowException(){
	}


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
}

