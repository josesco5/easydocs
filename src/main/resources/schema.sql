CREATE TABLE IF NOT EXISTS documents (
	"id" SERIAL NOT NULL,
	"subject" VARCHAR(2000),
	"path" TEXT,
	"folio" INT,
	PRIMARY KEY ("id")
);