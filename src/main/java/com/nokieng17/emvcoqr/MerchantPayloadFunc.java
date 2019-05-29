package com.nokieng17.emvcoqr;

public class MerchantPayloadFunc extends MerchantPayload {

	/***
	 * Creates the static merchant-presented QR with mandatory fields.
	 * 
	 * @param merchantGlobalUniqueIdentifier
	 *                                           The merchant global unique
	 *                                           identifier.
	 * @param merchantCategoryCode
	 *                                           The merchant category code.
	 * @param transactionCurrencyNumericCode
	 *                                           The transaction currency numeric
	 *                                           code.
	 * @param countryCode
	 *                                           The country code.
	 * @param merchantName
	 *                                           Name of the merchant.
	 * @param merchantCity
	 *                                           The merchant city.
	 * @param accountInformations
	 *                                           merchant Account information
	 * @return MerchantPayload A new instance of the {@link MerchantPayload}
	 *         class.
	 */
	public static MerchantPayload CreateStatic(
			MerchantAccountInformationMap accountInformations,
			int merchantCategoryCode,
			int transactionCurrencyNumericCode,
			String countryCode,
			String merchantName,
			String merchantCity) {
		return createPureQR(
				accountInformations,
				merchantCategoryCode,
				transactionCurrencyNumericCode,
				countryCode,
				merchantName,
				merchantCity
		)
				.setPointOfInitializationMethod(11);
	}

	/***
	 * Creates the dynamic merchant-presented QR with mandatory fields.
	 * 
	 * @param merchantGlobalUniqueIdentifier
	 *                                           The merchant global unique
	 *                                           identifier.
	 * @param merchantCategoryCode
	 *                                           The merchant category code.
	 * @param transactionCurrencyNumericCode
	 *                                           The transaction currency numeric
	 *                                           code.
	 * @param countryCode
	 *                                           The country code.
	 * @param merchantName
	 *                                           Name of the merchant.
	 * @param merchantCity
	 *                                           The merchant city.
	 * @param accountInformations
	 *                                           merchant Account information
	 * @return MerchantPayload A new instance of the {@link MerchantPayload}
	 *         class.
	 */
	public static MerchantPayload CreateDynamic(
			MerchantAccountInformationMap accountInformations,
			int merchantCategoryCode,
			int transactionCurrencyNumericCode,
			String countryCode,
			String merchantName,
			String merchantCity) {
		return createPureQR(
				accountInformations,
				merchantCategoryCode,
				transactionCurrencyNumericCode,
				countryCode,
				merchantName,
				merchantCity
		)
				.setPointOfInitializationMethod(12);
	}

	private static MerchantPayload createPureQR(
			MerchantAccountInformationMap accountInformations,
			int merchantCategoryCode,
			int transactionCurrencyNumericCode,
			String countryCode,
			String merchantName,
			String merchantCity) {
		return (MerchantPayload) new MerchantPayloadFunc()
				.setMerchantAccountInformation(accountInformations)
				.setPayloadFormatIndicator(1)
				.setMerchantCategoryCode(merchantCategoryCode)
				.setTransactionCurrency(transactionCurrencyNumericCode)
				.setCountyCode(countryCode)
				.setMerchantName(merchantName)
				.setMerchantCity(merchantCity);
	}

	/// <summary>
	/// Decodes QR data into a <see cref="MerchantPayload"/> instance.
	/// </summary>
	/// <param name="qrData">The qr data.</param>
	/// <exception cref="ArgumentNullException">If <paramref name="qrData"/> is
	/// <c>null</c> or an empty String.</exception>
	/// <exception cref="System.Security.SecurityException">If the CRC of the QR is
	/// invalid.</exception>
	/// <exception cref="ValidationException">If the payload is invalid.</exception>
	// public static MerchantPayload FromQR(String qrData) {
	// if (String.IsNullOrWhiteSpace(qrData)) {
	// throw new ArgumentNullException(nameof(qrData));
	// }
	//
	// var merchantDecoder = new MerchantDecoder();
	// var crc = merchantDecoder.ValidateCrc(qrData);
	// var tlvs = merchantDecoder.DecodeQR(qrData);
	// var payload = merchantDecoder.BuildPayload(tlvs);
	// payload.CRC = crc;
	//
	// var validationContext = new ValidationContext(payload);
	// Validator.ValidateObject(payload, validationContext, true);
	//
	// return payload;
	// }
}
