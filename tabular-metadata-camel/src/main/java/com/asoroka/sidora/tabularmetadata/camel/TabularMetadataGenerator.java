
package com.asoroka.sidora.tabularmetadata.camel;

import java.io.IOException;
import java.net.URL;

import org.apache.camel.Handler;
import org.apache.camel.Header;

import com.asoroka.sidora.tabularmetadata.TabularMetadata;

/**
 * A specialization of
 * {@link com.asoroka.sidora.tabularmetadata.TabularMetadataGenerator} for use
 * with Camel.
 * 
 * @author A. Soroka
 */
public class TabularMetadataGenerator extends com.asoroka.sidora.tabularmetadata.TabularMetadataGenerator {

	public static final String hasHeadersHeaderName = "com.asoroka.sidora.tabularmetadata.camel.hasHeadersHeaderName";
	public static final String dataUrlHeaderName = "com.asoroka.sidora.tabularmetadata.camel.dataUrlHeaderName";

	@Handler
	public TabularMetadata getMetadata(@Header(dataUrlHeaderName) final String dataUrl,
			@Header(hasHeadersHeaderName) final Boolean hasHeaders) throws IOException {
		return super.getMetadata(new URL(dataUrl), hasHeaders);
	}
}
