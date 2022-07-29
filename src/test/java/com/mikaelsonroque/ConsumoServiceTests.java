package com.mikaelsonroque;

import com.mikaelsonroque.models.ConsumoAnual;
import com.mikaelsonroque.models.ConsumoHistorico;
import com.mikaelsonroque.models.services.ConsumoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class ConsumoServiceTests {

    ConsumoService consumoService = new ConsumoService();

    private LinkedHashMap<String, Double> alimentarConsumoMeses(){
        LinkedHashMap<String, Double> meses = new LinkedHashMap<>();
        Random random = new Random();
        meses.put("JAN", (double) random.nextInt(50)); meses.put("FEV", (double) random.nextInt(50));
        meses.put("MAR", (double) random.nextInt(50)); meses.put("ABR", (double) random.nextInt(50));
        meses.put("MAI", (double) random.nextInt(50)); meses.put("JUN", (double) random.nextInt(50));
        meses.put("JUL", (double) random.nextInt(50)); meses.put("AGO", (double) random.nextInt(50));
        meses.put("SET", (double) random.nextInt(50)); meses.put("OUT", (double) random.nextInt(50));
        meses.put("NOV", (double) random.nextInt(50)); meses.put("DEZ", (double) random.nextInt(50));
        return meses;
    }

    ConsumoAnual consumoAnual2010 = new ConsumoAnual(2010, new LinkedHashMap<>());
    ConsumoAnual consumoAnual2011 = new ConsumoAnual(2011, new LinkedHashMap<>());
    ConsumoAnual consumoAnual2012 = new ConsumoAnual(2012, new LinkedHashMap<>());
    ConsumoAnual consumoAnual2013 = new ConsumoAnual(2013, new LinkedHashMap<>());

    ConsumoHistorico consumoHistorico = new ConsumoHistorico();

    @Test
    @DisplayName("Deve retornar o total de consumo por mês no período escolhido")
    public void determinarConsumoTotalMensalTest(){
        //Cenário
        consumoAnual2010.setListaMeses(alimentarConsumoMeses());
        consumoAnual2011.setListaMeses(alimentarConsumoMeses());
        consumoAnual2012.setListaMeses(alimentarConsumoMeses());
        consumoAnual2013.setListaMeses(alimentarConsumoMeses());
        consumoHistorico.setListaConsumo(List.of(consumoAnual2010, consumoAnual2011, consumoAnual2012, consumoAnual2013));


        //Execução
        HashMap<String, Double> mediaMeses = consumoService.calcularConsumoMedioMensal(consumoHistorico);
        System.out.println("Media valores gastos mensalmente: " + mediaMeses);

        //Verificação
        Assertions.assertThat(mediaMeses).hasSize(12);
        Assertions.assertThat(mediaMeses.get("JAN"))
                .isEqualTo((consumoAnual2010.getMeses().get("JAN") + consumoAnual2011.getMeses().get("JAN") +
                        consumoAnual2012.getMeses().get("JAN") + consumoAnual2013.getMeses().get("JAN"))
                        / consumoHistorico.getListaConsumo().size());
        Assertions.assertThat(mediaMeses.get("MAI"))
                .isEqualTo((consumoAnual2010.getMeses().get("MAI") + consumoAnual2011.getMeses().get("MAI") +
                        consumoAnual2012.getMeses().get("MAI") + consumoAnual2013.getMeses().get("MAI"))
                        / consumoHistorico.getListaConsumo().size());
        Assertions.assertThat(mediaMeses.get("NOV"))
                .isEqualTo((consumoAnual2010.getMeses().get("NOV") + consumoAnual2011.getMeses().get("NOV") +
                        consumoAnual2012.getMeses().get("NOV") + consumoAnual2013.getMeses().get("NOV"))
                        / consumoHistorico.getListaConsumo().size());
    }

    @Test
    @DisplayName("Deve verificar o calculo do mês que houve o maior gasto em energia elétrica")
    public void determinarMaiorGastoMensalTest(){
        //Cenário
        consumoAnual2010.setListaMeses(alimentarConsumoMeses());
        consumoAnual2011.setListaMeses(alimentarConsumoMeses());
        consumoAnual2012.setListaMeses(alimentarConsumoMeses());
        consumoAnual2013.setListaMeses(alimentarConsumoMeses());
        consumoHistorico.setListaConsumo(List.of(consumoAnual2010, consumoAnual2011, consumoAnual2012, consumoAnual2013));

        //Execução
        String maiorGastoMensal = consumoService.calcularMaiorGastoMensal(consumoHistorico);
        System.out.println("Ano e mês de maior gasto: " + maiorGastoMensal);

        //Verificações
        Assertions.assertThat(maiorGastoMensal).isNotNull();
        Assertions.assertThat(maiorGastoMensal).contains("Ano: ");
        Assertions.assertThat(maiorGastoMensal).contains("mes: ");
        Assertions.assertThat(maiorGastoMensal).contains("valor: R$");
    }

    @Test
    @DisplayName("Deve verificar qual o ano que houve o maior gasto em energia elétrica")
    public void determinarMaiorGastoAnualTest(){
        //Cenário
        consumoAnual2010.setListaMeses(alimentarConsumoMeses());
        consumoAnual2011.setListaMeses(alimentarConsumoMeses());
        consumoAnual2012.setListaMeses(alimentarConsumoMeses());
        consumoAnual2013.setListaMeses(alimentarConsumoMeses());
        consumoHistorico.setListaConsumo(List.of(consumoAnual2010, consumoAnual2011, consumoAnual2012, consumoAnual2013));

        //Execução
        String maiorGastoAnual = consumoService.calcularMaiorGastoAnual(consumoHistorico);
        System.out.println("Ano de maior gasto e valor: " + maiorGastoAnual);

        //Verificação
        Assertions.assertThat(maiorGastoAnual).isNotNull();
        Assertions.assertThat(maiorGastoAnual).contains("Ano: ");
        Assertions.assertThat(maiorGastoAnual).contains("valor gasto: R$");
    }
}
