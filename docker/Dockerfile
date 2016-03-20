from websphere-liberty:kernel

RUN /opt/ibm/wlp/bin/server create bonuspayout.results.api 

ADD server.xml /opt/ibm/wlp/usr/servers/bonuspayout.results.api

RUN /opt/ibm/wlp/bin/installUtility install --acceptLicense --to=usr bonuspayout.results.api

COPY BonusPayoutResultsApiService.war /opt/ibm/wlp/usr/servers/bonuspayout.results.api/dropins

ENV LOG_DIR=/logs

CMD ["/opt/ibm/wlp/bin/server", "run", "bonuspayout.results.api"]
