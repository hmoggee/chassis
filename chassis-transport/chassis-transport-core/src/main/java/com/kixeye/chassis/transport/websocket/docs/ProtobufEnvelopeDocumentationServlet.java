package com.kixeye.chassis.transport.websocket.docs;

/*
 * #%L
 * Chassis Transport Core
 * %%
 * Copyright (C) 2014 KIXEYE, Inc
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kixeye.chassis.transport.dto.Envelope;

/**
 * Generates protobuf envelope schema.
 * 
 * @author ebahtijaragic
 */
public class ProtobufEnvelopeDocumentationServlet extends HttpServlet {
	private static final long serialVersionUID = 5397593236087739762L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> schemas = new HashMap<String, String>();
		
		try {
			ProtobufSchemaGenerator.generateSchema(Envelope.class, schemas, null);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		resp.getWriter().println("package Transport;");
		resp.getWriter().println();
		
		for (String message : schemas.values()) {
			resp.getWriter().println(message);
		}
	}
}
