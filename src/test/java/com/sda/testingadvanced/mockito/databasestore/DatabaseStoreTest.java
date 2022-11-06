package com.sda.testingadvanced.mockito.databasestore;

import static org.assertj.core.api.Assertions.assertThat;
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
}