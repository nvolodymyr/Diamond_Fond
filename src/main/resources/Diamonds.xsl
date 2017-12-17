<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/gems">
        <html>
            <body>
                <table border="1">
                <xsl:for-each select="gem">
                    <xsl:value-of select="name"/>
                    <xsl:value-of select="preciousness"/>
                    <xsl:value-of select="origin"/>
                    <xsl:for-each select="visualParameters">
                        <xsl:value-of select="visualId"/>
                        <xsl:value-of select="color"/>
                        <xsl:value-of select="transparency"/>
                        <xsl:value-of select="gemCutting"/>
                    </xsl:for-each>
                    <xsl:value-of select="value"/>
                </xsl:for-each>
                </table>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>