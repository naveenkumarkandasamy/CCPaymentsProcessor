<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"
		omit-xml-declaration="yes"></xsl:output>
	<xsl:template match="/">
		<soapenv:Envelope
			xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
			<soapenv:Header />
			<soapenv:Body>
				<xsl:apply-templates />
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*" />
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="MM">
		<MM>
			<xsl:value-of select="format-number(.,'00')"></xsl:value-of>
		</MM>
	</xsl:template>
	<!-- <xsl:template match="SALUTL">
		<xsl:copy-of select="." />
		<ADDRTYPE>R</ADDRTYPE>
		<CLTADDR02></CLTADDR02>
		<CLTADDR03></CLTADDR03>
		<CLTADDR04></CLTADDR04>
		<CLTADDR05></CLTADDR05>
		<CLTPHONE02></CLTPHONE02>
		<DIRMAIL></DIRMAIL>
		<DOCNO></DOCNO>
		<MAILING></MAILING>
		<SECUITYNO></SECUITYNO>
		<SOE></SOE>
		<STATCODE></STATCODE>
		<VIP></VIP>
	</xsl:template> -->
</xsl:stylesheet>