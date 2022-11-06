package com.sda.testingadvanced.mockito.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
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
	private static final User USER = new User(USER_ID, "Jan", "Kowalski");

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserValidator userValidator;
	@InjectMocks
	private UserService userService;

	@Test
	void shouldGetUserById() {
		when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER));

		assertThat(userService.getUserById(USER_ID))
				.isNotNull()
				.isEqualTo(USER);
	}

	@Test
	void shouldThrowExceptionWhenUserNotFound() {
		assertThatThrownBy(()-> userService.getUserById(1L))
				.isInstanceOf(NoSuchElementException.class);
	}

	@Test
	void shouldAddValidUser() {
		//given
		when(userValidator.isUserValid(USER)).thenReturn(true);

		//when
		userService.addUser(USER);

		//then
		//verify that user repository add method has been called
		verify(userRepository, times(1)).addUser(USER);
	}

	@Test
	void shouldThrowExceptionAndNotCallRepositoryWhenAddingInvalidUser() {
		//given
		when(userValidator.isUserValid(USER)).thenReturn(false);

		//when
		assertThatThrownBy(()-> userService.addUser(USER))
				.isInstanceOf(IllegalArgumentException.class);

		//verifyNoInteractions(userRepository);
		verify(userRepository, never()).addUser(USER);
	}
}