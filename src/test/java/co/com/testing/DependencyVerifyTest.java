package co.com.testing;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.*;

public class DependencyVerifyTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Dependency dependency;

    @Test
    public void testSimpleVerify() {
        //nunca se ha ejecutado.
        verify(dependency, never()).getClassNameUpperCase();
        dependency.getClassNameUpperCase();
        //exactamente una vez.
        verify(dependency, times(1)).getClassNameUpperCase();
        //como mínimo 1 vez.
        verify(dependency, atLeast(1)).getClassNameUpperCase();
        dependency.getClassNameUpperCase();
        //como máximo 2 veces.
        verify(dependency, atMost(2)).getClassNameUpperCase();
    }

    @Test
    public void testParameters() {
        dependency.addTwo(3);
        //una vez con el parámetro 3
        verify(dependency, times(1)).addTwo(3);
        dependency.addTwo(4);
        //dos veces con cualquier parámetro
        verify(dependency, times(2)).addTwo(anyInt());
    }
}
