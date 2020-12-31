package de.htwsaar.prog3.carrental;

import lombok.Getter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class TestUiApplication extends CarRentalUiApplication {

    @Getter
    private final ConfigurableApplicationContext testApplicationContext;

    public TestUiApplication(ConfigurableApplicationContext testApplicationContext) {
        this.testApplicationContext = spy(testApplicationContext);
        // do not close the context for real as it is used in parallel
        // we can verify that a close was requested though
        doNothing().when(this.testApplicationContext).close();
    }

    @Override
    public void init() {
        ReflectionTestUtils.setField(this, "applicationContext", testApplicationContext);
    }
}
