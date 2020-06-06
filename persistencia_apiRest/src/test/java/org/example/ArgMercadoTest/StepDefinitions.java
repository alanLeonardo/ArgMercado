package org.example.ArgMercadoTest;

import org.example.ArgMercado.ApiApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@ComponentScan({"org.example.ArgMercado"})
public abstract class StepDefinitions {

}
