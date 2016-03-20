from websphere-liberty:kernel

RUN /opt/ibm/wlp/bin/server create bonuspayout.results.api 

ADD server.xml /opt/ibm/wlp/usr/servers/bonuspayout.results.api

RUN /opt/ibm/wlp/bin/installUtility install --acceptLicense --to=usr bonuspayout.results.api

COPY BonusPayoutResultsApiService.war /opt/ibm/wlp/usr/servers/bonuspayout.results.api/dropins

ENV WLP_OUTPUT_DIR=/opt/ibm/wlp/usr/servers/ CSV_DIR=/csv

CMD ["/opt/ibm/wlp/bin/server", "run", "bonuspayout.results.api"]
