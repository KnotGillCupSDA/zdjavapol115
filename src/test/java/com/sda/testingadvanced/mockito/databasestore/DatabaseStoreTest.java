package com.sda.testingadvanced.mockito.databasestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DatabaseStoreTest {

	private static final String DATA = "some data";
	@Mock
	DatabaseConnection databaseConnection;
	@InjectMocks
	DatabaseStore databaseStore;

	@Test
	void shouldAddDataForOpenedDatabaseConnection() {
		when(databaseConnection.isOpened()).thenReturn(true);

		assertThat(databaseStore.addData(DATA))
				.isEqualTo(1);

		verify(databaseConnection, times(1)).isOpened();
		verify(databaseConnection, never()).open();
	}

	@Test
	void shouldOpenDatabaseConnectionAndAddData() {

		when(databaseConnection.isOpened())
				.thenReturn(false)
				.thenReturn(true);

		//Sprawdź, czy operacja addData się powiodła
		assertThat(databaseStore.addData(DATA))
				.isEqualTo(1);

		//Sprawdź czy zawołano predykat isOpened() dwukrotnie
		verify(databaseConnection, times(2)).isOpened();

		//Zweyfikuj, że zawołano metodę open()
		verify(databaseConnection, times(1)).open();
	}

	@Test
	void shouldThrowExceptionWhenCantOpenDatabaseConnection() {
		//za każdym razem jak wolany jest predykat isOpened to zwróc false
		when(databaseConnection.isOpened()).thenReturn(false);

		//sprawdź, że dodanie czegokolwiek spowoduje rzucenie wyjątku o typie SdaException
		assertThatThrownBy(()-> databaseStore.addData(DATA))
				.isInstanceOf(SdaException.class);
	}
}