package aplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHttpRequest;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.ExchangeRateResponse;
import exceptions.ApiConnectionException;
import exceptions.CurrencyNotFoundException;

public class Aplication {

    public static void main(String[] args) {

        System.out.println("Welcome to the Currency Converter");
        System.out.println("===================================");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // 1. Carrega a API KEY
        String apiKey = System.getenv("api_conversor_key_exchange_rate_api");

        if (apiKey == null) {
            System.out.println("ERROR: Environment variable not found!");
            return;
        }

        // 2. Entrada da moeda base
        System.out.print("Enter the base currency (e.g., USD): ");
        String baseCurrency = sc.nextLine().toUpperCase();

        if (!baseCurrency.matches("[A-Z]{3}")) {
            System.out.println("Invalid code! Use abbreviations such as USD, EUR, BRL.");
            return;
        }

        // 3. Monta URL da API/ site da api/ minha chave key / End point / valor de busca
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;

        
        // 4. Obtendo a API busca e resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

       try {
    	   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    	   // 5. Mapear JSON para objeto Java
           ObjectMapper mapper = new ObjectMapper();
           ExchangeRateResponse exchangeRateResponse = mapper.readValue(response.body(),ExchangeRateResponse.class);
           
           System.out.println("Enter the value you wish to convert!");
           double valor = sc.nextDouble();
           
           
          
           System.out.print("Enter the destination currency (e.g., USD): ");
           String targetCurrency = sc.next().toUpperCase();

           // Verifica se existe no Map
           Map<String, Double> rates = exchangeRateResponse.getConversion_rates();

           if (!rates.containsKey(targetCurrency)) {
               throw new CurrencyNotFoundException("Coin not found: " + targetCurrency);
           }

           // Pega a taxa de conversão da moeda destino
           double taxa = rates.get(targetCurrency);

           // Calcula o valor convertido
           double valorConvertido = valor * taxa;
           
           System.out.printf("The value %.2f converted from %s to the currency %s is %.2f%n", valor, baseCurrency, targetCurrency, valorConvertido );
           
           
       } catch (Exception e ) {
    	   System.out.println("An error occurred.: " + e.getMessage());
       }
       
       
       
    }
}
