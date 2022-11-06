package com.sda.testingadvanced.mockito.captors.appender;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppenderTest {

	@Mock
	private Receiver receiver;

	@Test
	void shouldAppendAsSuffix() {
		Appender appender = new Appender(receiver, "abc");

		//playground
		when(receiver.b(10)).thenReturn("dupa10");
		lenient().when(receiver.b(11)).thenReturn("dupa11");
		System.out.println(receiver.b(10));

		appender.append("text");

		//assert that receiver.accept has been called with textabc
		verify(receiver, times(1)).accept("textabc");
	}
}