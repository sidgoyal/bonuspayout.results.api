from websphere-liberty:webProfile7

RUN mkdir -p /opt/ibm/wlp/usr/servers/bonuspayout.results.api/dropins

ADD server.xml /opt/ibm/wlp/usr/servers/bonuspayout.results.api

COPY BonusPayoutResultsApiService.war /opt/ibm/wlp/usr/servers/bonuspayout.results.api/dropins

CMD ["/opt/ibm/wlp/bin/server", run, "bonuspayout.results.api"]
