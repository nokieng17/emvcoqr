package com.nokieng17.emvcoqr;

import javax.validation.constraints.NotNull;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

/***
 * @author magma17 MerchantInfoLanguageTemplate If this template is present, it
 *         shall contain the Language Preference (ID "00") and Merchant
 *         Name—Alternate Language (ID "01"). It may contain the Merchant
 *         City—Alternate Language(ID "02").
 */
public class MerchantInfoLanguageTemplate {

	@EmvSpecification(Id = 0, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "LanguagePreference is null")
	public String languagePreference;

	@EmvSpecification(Id = 1, MaxLength = 25)
	@RequireIso8859
	@NotNull(message = "merchantNameAlternateLanguage is null")
	public String merchantNameAlternateLanguage;

	@EmvSpecification(Id = 2, MaxLength = 15)
	@RequireIso8859
	public String merchantCityAlternateLanguage;

	/***
	 * The language Preference shall contain 2 alphabetical characters coded to a
	 * value defined by [ISO 639]. The language preference. The value should
	 * represent the single language used to encode the Merchant Name—Alternate
	 * Language and the optional Merchant City—Alternate Language.
	 * 
	 * @param String
	 *                   languagePreference
	 * @return MerchantInfoLanguageTemplate
	 */
	public MerchantInfoLanguageTemplate setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
		return this;
	}

	/***
	 * sets the name of the merchant. The Merchant Name—Alternate Language should
	 * indicate the “doing business as” name for the merchant in the merchant’s
	 * local language.
	 * 
	 * @param String
	 *                   merchantNameAlternateLanguage The name of the merchant
	 *                   alternative language.
	 * @return MerchantInfoLanguageTemplate
	 */

	public MerchantInfoLanguageTemplate setMerchantNameAlternateLanguage(String merchantNameAlternateLanguage) {
		this.merchantNameAlternateLanguage = merchantNameAlternateLanguage;
		return this;
	}

	/***
	 * sets the language name of the merchant. If present, the Merchant
	 * City—Alternate Language should indicate the city in which the merchant
	 * transacts in the merchant’s local language.
	 * 
	 * @param String
	 *                   merchantCityAlternateLanguage The merchant city.
	 * @return MerchantInfoLanguageTemplate
	 */
	public MerchantInfoLanguageTemplate setMerchantCityAlternateLanguage(String merchantCityAlternateLanguage) {
		this.merchantCityAlternateLanguage = merchantCityAlternateLanguage;
		return this;
	}

}
