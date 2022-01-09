drop table if exists weather_history;
CREATE TABLE weather_history (
  weather_date date NOT NULL,
  weather_value VARCHAR(255) NULL,
  CONSTRAINT pk_weather_history PRIMARY KEY (weather_date)
);