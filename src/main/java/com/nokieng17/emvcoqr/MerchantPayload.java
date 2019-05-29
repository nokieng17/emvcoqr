package com.nokieng17.emvcoqr;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

public class MerchantPayload {

	@EmvSpecification(Id = 0, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "payloadFormatIndicator is null")
	public int payloadFormatIndicator = -1;

	@EmvSpecification(Id = 1, MaxLength = 2)
	@RequireIso8859
	@Min(value = 11, message = "MerchantAccountInformations is out of range [11~12]")
	@Max(value = 12, message = "MerchantAccountInformations is out of range [11~12]")
	public int pointOfInitializationMethod = -1;

	@NotNull(message = "merchantAccountInformations is null")
	public MerchantAccountInformationMap merchantAccountInformations;

	@EmvSpecification(Id = 52, MaxLength = 4)
	@RequireIso8859
	@NotNull(message = "MerchantCategoryCode is null")
	@PositiveOrZero(message = "MerchantCategoryCode is not positive or zero")
	public int merchantCategoryCode = -1;

	@EmvSpecification(Id = 58, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "CountyCode is null")
	public String countyCode;

	@EmvSpecification(Id = 59, MaxLength = 25)
	@RequireIso8859
	@NotNull(message = "MerchantName is null")
	public String merchantName;

	@EmvSpecification(Id = 60, MaxLength = 15)
	@RequireIso8859
	@NotNull(message = "MerchantCity is null")
	public String merchantCity;

	@EmvSpecification(Id = 61, MaxLength = 10)
	@RequireIso8859
	public String postalCode;

	// [EmvSpecification(64, IsParent = true)]
	// [ValidateObject]
	public MerchantInfoLanguageTemplate merchantLanguageInformation;

	@EmvSpecification(Id = 54, MaxLength = 13)
	@RequireIso8859
	@PositiveOrZero(message = "transactionAmount is not positive or zero")
	public double transactionAmount = 0d;

	@EmvSpecification(Id = 53, MaxLength = 3)
	@RequireIso8859
	@PositiveOrZero(message = "transactionCurrency is not positive or zero")
	public int transactionCurrency = -1;

	@EmvSpecification(Id = 55, MaxLength = 2)
	@RequireIso8859
	@Min(value = -1, message = "TipOrConvenienceIndicator is out of range [1~3]")
	@Max(value = 3, message = "TipOrConvenienceIndicator is out of range [1~3]")
	@PositiveOrZero(message = "TipOrConvenienceIndicator is not positive or zero")
	public int tipOrConvenienceIndicator = -1;

	@EmvSpecification(Id = 56, MaxLength = 13)
	@RequireIso8859
	public String valueOfConvenienceFeeFixed;

	public String valueOfConvenienceFeePercentage;

	// [EmvSpecification(62, IsParent = true)]
	// [ValidateObject]
	public MerchantAdditionalData additionalData;

	// [EmvSpecification(91, IsParent = true)]
	// [ValidateObject]
	public MerchantUnreservedMap unreservedTemplate;

	// @EmvSpecification(Id = 63, MaxLength = 4)
	// @RequireIso8859
	// private String crc;

	/// <summary>
	/// Generates the EMV(R) compliant payload that can be written into a QR code.
	/// </summary>
	/// <returns>Returns a <see cref="string"/> that contains the payload that can
	/// be written into a QR code.</returns>
	/// <exception cref="InvalidOperationException">If the payload fails
	/// validation.</exception>
	// public String GeneratePayload()
	// {
	// var validationContext = new ValidationContext(this);
	// var errors = Validate(validationContext);
	// if (errors.Any())
	// {
	// var errorMessageBuilder = new StringBuilder();
	// errorMessageBuilder.AppendLine(LibraryResources.ValidationError);
	//
	// foreach (var item in errors)
	// {
	// errorMessageBuilder.AppendLine(item.ErrorMessage);
	// }
	//
	// throw new InvalidOperationException(errorMessageBuilder.ToString());
	// }
	//
	// var payload = new MerchantEncoder().GeneratePayload(this);
	// return payload;
	// }

	/***
	 * sets merchant payload version Defines the version of the QR Code template and
	 * hence the conventions on the identifiers, lengths, and values. The Payload
	 * Format Indicator shall contain a value of "01". All other values are RFU.
	 * 
	 * @param int
	 *            payloadFormatIndicator
	 * @return MerchantPayload
	 */

	public MerchantPayload setPayloadFormatIndicator(int payloadFormatIndicator) {
		this.payloadFormatIndicator = payloadFormatIndicator;
		return this;
	}

	/***
	 * Identifies the communication technology (here QR Code) and whether the data
	 * is static or dynamic The Point of Initiation Method has a value of "11" for
	 * static QR Codes anda value of "12" for dynamic QR Codes.
	 * {@value com.nokieng17.emvcoqr.MerchantPayload#pointOfInitializationMethod}
	 * The value of "11" is used when the same QR Code is shown for more than one
	 * transaction. The value of "12" is used when a new QR Code is shown for each
	 * transaction
	 * 
	 * @param int
	 *            pointOfInitializationMethod
	 * @return MerchantPayload
	 */

	public MerchantPayload setPointOfInitializationMethod(int pointOfInitializationMethod) {
		this.pointOfInitializationMethod = pointOfInitializationMethod;
		return this;
	}

	public MerchantPayload addMerchantAccountInformation(int id,
			MerchantAccountInformation merchantAccountInformation) {
		if (null == this.merchantAccountInformations) {
			this.merchantAccountInformations = new MerchantAccountInformationMap();
		}
		this.merchantAccountInformations.put(id, merchantAccountInformation);
		return this;
	}

	public MerchantPayload setMerchantAccountInformation(MerchantAccountInformationMap accounts) {
		if (null == this.merchantAccountInformations) {
			this.merchantAccountInformations = new MerchantAccountInformationMap();
		} else {
			this.merchantAccountInformations.clear();
		}
		this.merchantAccountInformations.putAll(accounts);
		accounts.clear();
		return this;
	}

	/***
	 * As defined by [ISO 18245] and assigned by the Acquirer
	 * 
	 * @param int
	 *            merchantCategoryCode
	 * @return MerchantPayload
	 */
	public MerchantPayload setMerchantCategoryCode(int merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
		return this;
	}

	/***
	 * Indicates the currency code of the transaction.
	 * 
	 * @see com.nokieng17.emvcoqr.iso.Iso4217Currency A 3-digit numeric value, as
	 *      defined by [ISO 4217]. This value will be used by the mobile application
	 *      to display a recognizable currency to the consumer whenever an amount is
	 *      being displayed or whenever the consumer is prompted to enter an amount.
	 * @param int
	 *            transactionCurrency
	 * @return MerchantPayload
	 */
	public MerchantPayload setTransactionCurrency(int transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
		return this;
	}

	/***
	 * Transaction Amount The transaction amount, if known. For instance, "99.34".
	 * If present, this value is displayed to the consumer by the mobile application
	 * when processing the transaction.If this data object is not present, the
	 * consumer is prompted to input the transaction amount to be paid to the
	 * merchant.
	 * 
	 * @param double
	 *            transactionAmount
	 * @return MerchantPayload
	 */
	public MerchantPayload setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
		return this;
	}

	/***
	 * Tips or Convenience Indicator Indicates whether the consumer will be prompted
	 * to enter a tip or whether the merchant has determined that a flat, or
	 * percentage convenience fee is charged.
	 * 
	 * @param int
	 *            tipOrConvenienceIndicator
	 * @return MerchantPayload
	 */
	public MerchantPayload setTipOrConvenienceIndicator(int tipOrConvenienceIndicator) {
		this.tipOrConvenienceIndicator = tipOrConvenienceIndicator;
		return this;
	}

	/***
	 * The fixed amount convenience fee when
	 * {@link com.nokieng17.emvcoqr.MerchantPayload#tipOrConvenienceIndicator}
	 * indicates a flat convenience fee For example, "9.85", indicating that this
	 * fixed amount (in the transaction currency) will be charged on top of the
	 * transaction amount.
	 * 
	 * @param String
	 *                   valueOfConvenienceFeeFixed
	 * @return MerchantPayload
	 */
	public MerchantPayload setValueOfConvenienceFeeFixed(String valueOfConvenienceFeeFixed) {
		this.valueOfConvenienceFeeFixed = valueOfConvenienceFeeFixed;
		return this;
	}

	/***
	 * The percentage convenience fee when
	 * {@link com.nokieng17.emvcoqr.MerchantPayload#tipOrConvenienceIndicator}
	 * indicates a percentage convenience fee. For example, "3.00" indicating that a
	 * convenience fee of 3% of the transaction amount will be charged, on top of
	 * the transaction amount.
	 * 
	 * @param String
	 *                   valueOfConvenienceFeePercentage
	 * @return MerchantPayload
	 */
	public MerchantPayload setValueOfConvenienceFeePercentage(String valueOfConvenienceFeePercentage) {
		this.valueOfConvenienceFeePercentage = valueOfConvenienceFeePercentage;
		return this;
	}

	/***
	 * Indicates the country of the merchant acceptance device.
	 * 
	 * @see com.nokieng17.emvcoqr.iso.Iso3166Countries A 2-character alpha value, as
	 *      defined by [ISO 3166-1 alpha 2] and assigned by the Acquirer.The country
	 *      may be displayed to the consumer by the mobile application when
	 *      processing the transaction
	 * @param String
	 *                   countyCode
	 * @return MerchantPayload
	 */
	public MerchantPayload setCountyCode(String countyCode) {
		this.countyCode = countyCode;
		return this;
	}

	/***
	 * The “doing business as” name for the merchant, recognizable to the
	 * consumer.This name may be displayed to the consumer by the mobile application
	 * when processing the transaction.
	 * 
	 * @param String
	 *                   merchantName
	 * @return MerchantPayload
	 */
	public MerchantPayload setMerchantName(String merchantName) {
		this.merchantName = merchantName;
		return this;
	}

	/***
	 * City of operations for the merchant. This name may be displayed to the
	 * consumer by the mobile application when processing the transaction.
	 * 
	 * @param String
	 *                   merchantCity
	 * @return MerchantPayload
	 */
	public MerchantPayload setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
		return this;
	}

	/***
	 * Zip code or Pin code or Postal code of the merchant. If present, this value
	 * may be displayed to the consumer by the mobile application when processing
	 * the transaction.
	 * 
	 * @param String
	 *                   postalCode
	 * @return MerchantPayload
	 */
	public MerchantPayload setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public MerchantPayload setAdditionalData(MerchantAdditionalData additionalData) {
		this.additionalData = additionalData;
		return this;
	}

	/***
	 * Merchant Name and potentially other merchant related information in an
	 * alternate language, typically the local language.
	 * 
	 * @param MerchantInfoLanguageTemplate
	 *                                         merchantInformation
	 * @return MerchantPayload
	 */
	public MerchantPayload setMerchantLanguageInformation(MerchantInfoLanguageTemplate merchantInformation) {
		this.merchantLanguageInformation = merchantInformation;
		return this;
	}

	/***
	 * sets the unreserved template.
	 * 
	 * @param MerchantUnreservedDictionary
	 *                                         unreservedTemplate The unreserved
	 *                                         template.
	 * @return MerchantPayload
	 */
	public MerchantPayload setUnreservedTemplate(int id, MerchantUnreservedTemplate unreservedTemplate) {
		if (null == this.unreservedTemplate) {
			this.unreservedTemplate = new MerchantUnreservedMap();
		}
		this.unreservedTemplate.put(id, unreservedTemplate);
		return this;
	}

	/***
	 * Checksum calculated over all the data objects included in the QR Code.
	 * 
	 * @param String
	 *                   cRC.
	 * @return MerchantPayload
	 */
	// public MerchantPayload setCRC(String cRC) {
	// this.crc = cRC;
	// return this;
	// }

}
