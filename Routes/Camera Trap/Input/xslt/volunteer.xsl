<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2013sp1

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <researcher xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <xsl:attribute name="xsi:noNamespaceSchemaLocation"></xsl:attribute>
            <xsl:for-each select="CameraTrapDeployment">
                <xsl:variable name="var2_cur" select="."/>
                <xsl:for-each select="ImageSequence">
                    <xsl:variable name="var1_cur" select="."/>
                    <xsl:for-each select="VolunteerIdentifications/Identification">
                        <xsl:value-of select="concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat(concat('Volunteer,', string($var2_cur/CameraDeploymentID)), ','), string($var1_cur/ImageSequenceId)), ','), string($var1_cur/ImageSequenceBeginTime)), ','), string($var1_cur/ImageSequenceEndTime)), ','), '&quot;'), string(SpeciesScientificName)), '&quot;'), ','), '&quot;'), string(SpeciesCommonName)), '&quot;'), ','), ','), ','), ','), string(number(string(Count)))), ','), ','), ','), '&#xa;')"/>
                    </xsl:for-each>
                </xsl:for-each>
            </xsl:for-each>
        </researcher>
    </xsl:template>
</xsl:stylesheet>
