package com.hps.integrator.entities.serialization;

import com.hps.integrator.entities.credit.HpsCreditCard;

@SuppressWarnings("unused")
public class HpsToken {

	private String object;
	private String token_type;
	private String token_value;
	private String token_expire;
	private Card card;
	private HpsError error;

	public HpsToken() {}
	
	public HpsToken(String number, String cvc, int expMonth, int expYear)
	{
		this.object = "token";
		this.token_type = "supt";
		this.card = new Card(number, cvc, expMonth, expYear);
	}
	
	public HpsToken(HpsCreditCard card)
	{
		this.object = "token";
		this.token_type = "supt";
		this.card = new Card(card.getNumber(), card.getCvv(), card.getExpMonth(), card.getExpYear());		
	}
	
	public HpsError getError() {
		return error;
	}
	
	public String getTokenType() {
		return token_type;
	}

	public String getTokenValue() {
		return token_value;
	}

	public String getTokenExpire() {
		return token_expire;
	}

	public Card getCard() {
		return card;
	}

	class Card {
		
		private String number;
		private String cvc;
		private int exp_month;
		private int exp_year;
		
		public Card()
		{
			
		}
		
		public Card(String number, String cvc, int expMonth, int expYear)
		{
			this.number = number;
			this.cvc = cvc;
			this.exp_month = expMonth;
			this.exp_year = expYear;
		}

		public String getNumber() {
			return number;
		}		
	}
}
