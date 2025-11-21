
package entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse{
	private String base_code;
	private Map<String, Double> conversion_rates;
	

	//construtor vazio
	public ExchangeRateResponse() {
		
	}


	public String getBase_code() {
		return base_code;
	}


	public void setBase_code(String base_code) {
		this.base_code = base_code;
	}


	public Map<String, Double> getConversion_rates() {
		return conversion_rates;
	}


	public void setConversion_rates(Map<String, Double> conversion_rates) {
		this.conversion_rates = conversion_rates;
	}


	@Override
	public String toString() {
		return "ExchangeRateResponse [base_code=" + base_code + ", conversion_rates=" + conversion_rates + "]";
	}
	

	
}
