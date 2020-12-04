import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestedMethodsTest
{
    private static final String INPUT_DATA = "тестовая строка с буковками1 и словами, english words";
    private static final String EXPECTED = "руяб1ел,englihword";

    private void mainTestCase( String actual ) {
        for( int i = 0; i < EXPECTED.length(); i++ )
        {
            String ch = String.valueOf( actual.charAt( i ));
            Assertions.assertTrue( EXPECTED.contains( ch ) );
        }
    }

    @Test
    @DisplayName( "method1 - через HashMap< буква, кол-во повторений>")
    void testMethod1() {
        mainTestCase( TestedMethods.method1( INPUT_DATA ));
    }

    @Test
    @DisplayName( "method2 - метод replaceAll()" )
    void testMethod2() {
        mainTestCase( TestedMethods.method2( INPUT_DATA ));
    }

    @Test
    @DisplayName( "method3 - сравнение результатов indexOf() и lastIndexOf()" )
    void testMethod3() {
        mainTestCase( TestedMethods.method3( INPUT_DATA ));
    }

    @Test
    @DisplayName( "method4 - regEx" )
    void testMethod4() {
        mainTestCase( TestedMethods.method4( INPUT_DATA ));
    }

    @Test
    @DisplayName( "method5 - Stream API" )
    void testMethod5() {
        mainTestCase( TestedMethods.method5( INPUT_DATA ));
    }
}
