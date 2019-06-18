<h1>EMVCO QR generator Java lib</h1>

<p>People have made emvco qr encoder and decoder public, and I have translated the public project written in C# code into java. Thus I should publish this project to public too</p>

<h3>crccalc lib</h3>
I have folk this lib, and my folk repo is here https://github.com/nokieng17/crcjava

The constructur of `CrcCalculator` must be visible ot public

<h5>import crccalc local project into mvn</h5>
<code>mvn install:install-file -Dfile="[DIR crccalc.jar]" 
		-DgroupId="com.nokieng17" -DartifactId=crccalc -Dversion="1.0.0" -Dpackaging=jar 
		-DgeneratePom=true</code>


<h1>Sample and Test Code</h1>
<p>Here is the sample code that compare the output payload to specifict template of emvco</p>


	@Test
	void testPayloadWithSpecificationSample()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		String origin = "00020101021229300012D156000000000510A93FO3230Q31280012D15600000001030812345678520441115802CN5914BEST TRANSPORT6007BEIJING64200002ZH0104最佳运输0202北京540523.7253031565502016233030412340603***0708A60086670902ME91320016A0112233449988770708123456786304A13A";
		
		MerchantPayload payload = MerchantPayloadFunc.CreateDynamic(
				new MerchantAccountInformationMap()
						.add(
								29,
								new MerchantAccountInformation()
										.setGlobalUniqueIdentifier("D15600000000")
										.addPaymentNetworkSpecifics(5, "A93FO3230Q"))
						.add(31, new MerchantAccountInformation()
								.setGlobalUniqueIdentifier("D15600000001")
								.addPaymentNetworkSpecifics(3, "12345678")),
				4111,
				new Iso4217Currency.MexicoPesoextends().getNumericCode(),
				Iso3166Countries.CHINA,
				"BEST TRANSPORT",
				"BEIJING");
		payload.setTransactionCurrency(new Iso4217Currency.Chinaextends().getNumericCode());
		payload.setTransactionAmount(23.72d);
		payload.setTipOrConvenienceIndicator(1);
		payload.setMerchantLanguageInformation(
				new MerchantInfoLanguageTemplate()
						.setLanguagePreference("ZH")
						.setMerchantNameAlternateLanguage("最佳运输")
						.setMerchantCityAlternateLanguage("北京"));
		payload.setAdditionalData(
				new MerchantAdditionalData()
						.setStoreLabel("1234")
						.setCustomerLabel("***")
						.setTerminalLabel("A6008667")
						.setAdditionalConsumerDataRequest("ME"));
		PayloadEncoding encode = new PayloadEncoding();
		payload.setUnreservedTemplate(91, new MerchantUnreservedTemplate()
				.setGlobalUniqueIdentifier("A011223344998877")
				.setContextSpecificData(7, "12345678"));
		String qr = encode.GeneratePayload(payload);

		System.out.println(qr);
		System.out.println(origin);

		assertTrue("Bad qr", qr.equals(origin));
	}
