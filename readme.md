<h1>EMVCO QR generator Java lib</h1>

<p>People have made emvco qr encoder and decoder public, and I have translated the public project written in C# code into java. Thus I should publish this project to public too</p>

<h3>crccalc lib</h3>
I have folk this lib, and my folk repo is here https://github.com/nokieng17/crcjava

The constructur of `CrcCalculator` must be visible ot public

<h5>import crccalc local project into mvn</h5>
<code>mvn install:install-file -Dfile="[DIR crccalc.jar]" 
		-DgroupId="com.nokieng17" -DartifactId=crccalc -Dversion="1.0.0" -Dpackaging=jar 
		-DgeneratePom=true</code>
