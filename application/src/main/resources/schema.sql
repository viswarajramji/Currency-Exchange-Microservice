CREATE TABLE exchange_reference_rate
  (
     exchange_reference_rate_id VARCHAR(36) NOT NULL,
     base_currency_id           VARCHAR(3) NOT NULL,
     effective_date             DATE NOT NULL,
     PRIMARY KEY (exchange_reference_rate_id)
  );

CREATE TABLE exchange_reference_rate_detail
  (
     exchange_reference_rate_detail_id VARCHAR(36) NOT NULL,
     currency_id                       VARCHAR(3) NOT NULL,
     exchange_rate                     DOUBLE NOT NULL,
     exchange_reference_rate_id        VARCHAR(36) NOT NULL,
     PRIMARY KEY (exchange_reference_rate_detail_id)
  );

CREATE TABLE read_access
  (
     read_access_id                    VARCHAR(36) NOT NULL,
     exchange_reference_rate_detail_id VARCHAR(36) NOT NULL,
     read_by                           VARCHAR(255) NOT NULL,
     read_on                           TIMESTAMP NOT NULL,
     PRIMARY KEY (read_access_id)
  );

ALTER TABLE exchange_reference_rate
  ADD CONSTRAINT uniqueeffectivedate UNIQUE (effective_date);

ALTER TABLE exchange_reference_rate_detail
  ADD CONSTRAINT uniqueexchangereferenceandcurrency UNIQUE (
  exchange_reference_rate_id, currency_id); 

ALTER TABLE exchange_reference_rate_detail
  ADD CONSTRAINT exchange_reference_rate_references_exchange_reference_rate_detail FOREIGN KEY (
  exchange_reference_rate_id) REFERENCES exchange_reference_rate;

ALTER TABLE read_access
  ADD CONSTRAINT exchange_reference_rate_detail_reference_read_access FOREIGN KEY (
  exchange_reference_rate_detail_id) REFERENCES exchange_reference_rate_detail; 
  
CREATE VIEW read_report_view
AS
  SELECT A.read_access_id AS READ_ACCESS_ID,
         B.effective_date AS EFFECTIVE_DATE,
         B.currency_id    AS CURRENCY_ID,
         A.read_on        AS READ_ON,
         A.read_by        AS READ_BY
  FROM   read_access A
         INNER JOIN (SELECT effective_date,
                            base_currency_id,
                            currency_id,
                            exchange_reference_rate_detail_id
                     FROM   exchange_reference_rate A
                            INNER JOIN exchange_reference_rate_detail B
                                    ON A.exchange_reference_rate_id =
                                       B.exchange_reference_rate_id) AS B
                 ON A.exchange_reference_rate_detail_id =
                    B.exchange_reference_rate_detail_id; 
