<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BUNKER OPERATIONS - CORE INTERACTIVE CONSOLE</title>
        <style>
            /* ARCHITECTURE VISUAL STANDARD / TERMINAL INTERFACE
               Designed to simulate an industrial OT (Operational Technology) control panel
            */
            body {
                background-color: #0b0f19;
                color: #34d399;
                font-family: 'Courier New', Courier, monospace;
                padding: 30px;
                margin: 0;
            }
            .container {
                max-width: 950px;
                margin: 0 auto;
                border: 2px solid #10b981;
                box-shadow: 0 0 20px rgba(16, 185, 129, 0.2);
                padding: 25px;
                background-color: #111827;
                border-radius: 8px;
            }
            h1 {
                color: #10b981;
                text-align: center;
                text-transform: uppercase;
                letter-spacing: 3px;
                margin-top: 0;
                border-bottom: 2px dashed #10b981;
                padding-bottom: 15px;
            }
            .panel-info {
                background-color: #1f2937;
                border-left: 5px solid #60a5fa;
                padding: 15px;
                margin-bottom: 25px;
                color: #cbd5e1;
                font-size: 0.95em;
            }
            .btn-trigger {
                display: block;
                width: 100%;
                background-color: #064e3b;
                color: #34d399;
                border: 2px solid #10b981;
                padding: 14px;
                font-size: 1.1em;
                font-weight: bold;
                font-family: 'Courier New', Courier, monospace;
                cursor: pointer;
                text-transform: uppercase;
                letter-spacing: 2px;
                transition: all 0.3s ease;
                margin-bottom: 25px;
            }
            .btn-trigger:hover {
                background-color: #10b981;
                color: #0b0f19;
                box-shadow: 0 0 15px #10b981;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #030712;
            }
            th, td {
                border: 1px solid #374151;
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #1f2937;
                color: #10b981;
                text-transform: uppercase;
                font-size: 0.9em;
            }
            .crypto-value {
                color: #f43f5e;
                font-weight: bold;
                word-break: break-all;
            }
            .token-value {
                color: #60a5fa;
                word-break: break-all;
            }
            .alert-breach {
                background-color: #7f1d1d;
                color: #fca5a5;
                padding: 15px;
                border: 1px solid #ef4444;
                margin-bottom: 20px;
                text-align: center;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Bunker Final - Security Core Terminal</h1>
            
            <div class="panel-info">
                <strong>Architecture Status:</strong> Active EJB Stateless context & mapped Servlets. 
                The isolation perimeter of the <code>WEB-INF</code> directory ensures that the deployment descriptor 
                <code>web.xml</code> remains fully inaccessible from external client URL requests.
            </div>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert-breach">
                    ⚠️ CRITICAL ALERT: <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="randomword" method="POST">
                <button type="submit" class="btn-trigger">
                    ⚡ Execute Secure Data Extraction ⚡
                </button>
            </form>

            <% if (request.getAttribute("aleman") != null) { %>
                <h2>[+] Decrypted Linguistic Data Sets</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Language Module</th>
                            <th>Extracted Term</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td>GERMAN (PIVOT)</td><td><%= request.getAttribute("aleman") %></td></tr>
                        <tr><td>SPANISH (TARGET)</td><td><%= request.getAttribute("espanol") %></td></tr>
                        <tr><td>MANDARIN CHINESE</td><td><%= request.getAttribute("mandarin") %></td></tr>
                        <tr><td>JAPANESE</td><td><%= request.getAttribute("japones") %></td></tr>
                        <tr><td>HINDI</td><td><%= request.getAttribute("hindi") %></td></tr>
                    </tbody>
                </table>

                <h2>[+] Cryptographic Integrity Verifications</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Security Vector</th>
                            <th>Computed Output Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>SHA-256 DIGITAL SIGNATURE (Based on German Pivot)</td>
                            <td class="crypto-value"><%= request.getAttribute("sha256") %></td>
                        </tr>
                        <tr>
                            <td>GLOBAL UNIQUE SESSION TOKEN (UUID)</td>
                            <td class="token-value"><%= request.getAttribute("token") %></td>
                        </tr>
                        <tr>
                            <td>EPHEMERAL OT TIME-KEY (CPU nanoseconds)</td>
                            <td class="token-value"><%= request.getAttribute("key") %></td>
                        </tr>
                    </tbody>
                </table>

                <h2>[+] Operational Safety Directives (CYBORG OT TIPS)</h2>
                <div class="panel-info" style="border-left: 5px solid #10b981; background-color: #030712;">
                    <strong>SYSTEM INSIGHT:</strong> <%= request.getAttribute("tip") %>
                </div>
            <% } %>
        </div>
    </body>
</html>