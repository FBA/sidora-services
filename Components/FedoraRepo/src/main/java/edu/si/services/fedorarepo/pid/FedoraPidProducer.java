/*
 * Copyright 2014 Smithsonian Institution.
 *
 * Permission is granted to use, copy, modify,
 * and distribute this software and its documentation for educational, research
 * and non-profit purposes, without fee and without a signed licensing
 * agreement, provided that this notice, including the following two paragraphs,
 * appear in all copies, modifications and distributions.  For commercial
 * licensing, contact the Office of the Chief Information Officer, Smithsonian
 * Institution, 380 Herndon Parkway, MRC 1010, Herndon, VA. 20170, 202-633-5256.
 *
 * This software and accompanying documentation is supplied "as is" without
 * warranty of any kind. The copyright holder and the Smithsonian Institution:
 * (1) expressly disclaim any warranties, express or implied, including but not
 * limited to any implied warranties of merchantability, fitness for a
 * particular purpose, title or non-infringement; (2) do not assume any legal
 * liability or responsibility for the accuracy, completeness, or usefulness of
 * the software; (3) do not represent that use of the software would not
 * infringe privately owned rights; (4) do not warrant that the software
 * is error-free or will be maintained, supported, updated or enhanced;
 * (5) will not be liable for any indirect, incidental, consequential special
 * or punitive damages of any kind or nature, including but not limited to lost
 * profits or loss of data, on any basis arising from contract, tort or
 * otherwise, even if any of the parties has been warned of the possibility of
 * such loss or damage.
 */
package edu.si.services.fedorarepo.pid;

import com.yourmediashelf.fedora.client.FedoraClient;
import com.yourmediashelf.fedora.client.request.GetNextPID;
import com.yourmediashelf.fedora.client.response.GetNextPIDResponse;
import edu.si.services.fedorarepo.Headers;
import edu.si.services.fedorarepo.base.FedoraProducer;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jshingler
 */
class FedoraPidProducer extends FedoraProducer
{

    private static final Logger LOG = LoggerFactory.getLogger(FedoraPidProducer.class);
    private final FedoraPidEndpoint endpoint;

    public FedoraPidProducer(FedoraPidEndpoint endpoint)
    {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) throws Exception
    {
        GetNextPID request = FedoraClient.getNextPID();

        if (this.hasParam(this.endpoint.getNamespace()))
        {
            request.namespace(this.endpoint.getNamespace());
        }//end if

        GetNextPIDResponse pid = request.execute();

        Message in = exchange.getIn();
        Map<String, Object> headers = in.getHeaders();

        headers.put(Headers.PID, pid.getPid());
        headers.put(Headers.STATUS, pid.getStatus());

        LOG.debug(String.format("Pid: %s Status = %d", pid.getPid(), pid.getStatus()));

        Message out = exchange.getOut();

        out.setBody(in.getBody());
        out.setHeaders(headers);
    }

}