package com.sda.testingadvanced.mockito.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	private static final Long USER_ID = 1234L;

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@Test
	void shouldGetUserById() {
		User user = new User(USER_ID, "Jan", "Kowalski");
		when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

		assertThat(userService.getUserById(USER_ID))
				.isNotNull()
				.isEqualTo(user);
	}
}