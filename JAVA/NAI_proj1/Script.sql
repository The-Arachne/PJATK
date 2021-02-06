--<ScriptOptions statementTerminator=";"/>

DROP TABLE SYS.SYSCOLUMNS;

DROP TABLE SYS.SYSTABLES;

DROP TABLE SYS.SYSDEPENDS;

DROP TABLE SYS.SYSSTATISTICS;

DROP TABLE SYS.SYSSCHEMAS;

DROP TABLE SYS.SYSCONGLOMERATES;

DROP TABLE SYS.SYSUSERS;

DROP TABLE SYS.SYSROUTINEPERMS;

DROP TABLE SYS.SYSCONSTRAINTS;

DROP TABLE SYS.SYSTABLEPERMS;

DROP TABLE SYS.SYSTRIGGERS;

DROP TABLE SYS.SYSVIEWS;

DROP TABLE SYS.SYSCOLPERMS;

DROP TABLE SYS.SYSPERMS;

DROP TABLE SYS.SYSSEQUENCES;

DROP TABLE SYS.SYSFOREIGNKEYS;

DROP TABLE SYS.SYSFILES;

DROP TABLE SYS.SYSSTATEMENTS;

DROP TABLE SYS.SYSKEYS;

DROP TABLE SYSIBM.SYSDUMMY1;

DROP TABLE SYS.SYSALIASES;

DROP TABLE SYS.SYSROLES;

DROP TABLE SYS.SYSCHECKS;

CREATE TABLE SYS.SYSCOLUMNS (
		REFERENCEID CHAR(36) NOT NULL,
		COLUMNNAME VARCHAR(128) NOT NULL,
		COLUMNNUMBER INTEGER NOT NULL,
		COLUMNDATATYPE null NOT NULL,
		COLUMNDEFAULT null,
		COLUMNDEFAULTID CHAR(36),
		AUTOINCREMENTVALUE BIGINT,
		AUTOINCREMENTSTART BIGINT,
		AUTOINCREMENTINC BIGINT,
		AUTOINCREMENTCYCLE null
	);

CREATE TABLE SYS.SYSTABLES (
		TABLEID CHAR(36) NOT NULL,
		TABLENAME VARCHAR(128) NOT NULL,
		TABLETYPE CHAR(1) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		LOCKGRANULARITY CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSDEPENDS (
		DEPENDENTID CHAR(36) NOT NULL,
		DEPENDENTFINDER null NOT NULL,
		PROVIDERID CHAR(36) NOT NULL,
		PROVIDERFINDER null NOT NULL
	);

CREATE TABLE SYS.SYSSTATISTICS (
		STATID CHAR(36) NOT NULL,
		REFERENCEID CHAR(36) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		CREATIONTIMESTAMP TIMESTAMP NOT NULL,
		TYPE CHAR(1) NOT NULL,
		VALID null NOT NULL,
		COLCOUNT INTEGER NOT NULL,
		STATISTICS null NOT NULL
	);

CREATE TABLE SYS.SYSSCHEMAS (
		SCHEMAID CHAR(36) NOT NULL,
		SCHEMANAME VARCHAR(128) NOT NULL,
		AUTHORIZATIONID VARCHAR(128) NOT NULL
	);

CREATE TABLE SYS.SYSCONGLOMERATES (
		SCHEMAID CHAR(36) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		CONGLOMERATENUMBER BIGINT NOT NULL,
		CONGLOMERATENAME VARCHAR(128),
		ISINDEX null NOT NULL,
		DESCRIPTOR null,
		ISCONSTRAINT null,
		CONGLOMERATEID CHAR(36) NOT NULL
	);

CREATE TABLE SYS.SYSUSERS (
		USERNAME VARCHAR(128) NOT NULL,
		HASHINGSCHEME VARCHAR(32672) NOT NULL,
		PASSWORD VARCHAR(32672) NOT NULL,
		LASTMODIFIED TIMESTAMP NOT NULL
	);

CREATE TABLE SYS.SYSROUTINEPERMS (
		ROUTINEPERMSID CHAR(36) NOT NULL,
		GRANTEE VARCHAR(128) NOT NULL,
		GRANTOR VARCHAR(128) NOT NULL,
		ALIASID CHAR(36) NOT NULL,
		GRANTOPTION CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSCONSTRAINTS (
		CONSTRAINTID CHAR(36) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		CONSTRAINTNAME VARCHAR(128) NOT NULL,
		TYPE CHAR(1) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		STATE CHAR(1) NOT NULL,
		REFERENCECOUNT INTEGER NOT NULL
	);

CREATE TABLE SYS.SYSTABLEPERMS (
		TABLEPERMSID CHAR(36) NOT NULL,
		GRANTEE VARCHAR(128) NOT NULL,
		GRANTOR VARCHAR(128) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		SELECTPRIV CHAR(1) NOT NULL,
		DELETEPRIV CHAR(1) NOT NULL,
		INSERTPRIV CHAR(1) NOT NULL,
		UPDATEPRIV CHAR(1) NOT NULL,
		REFERENCESPRIV CHAR(1) NOT NULL,
		TRIGGERPRIV CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSTRIGGERS (
		TRIGGERID CHAR(36) NOT NULL,
		TRIGGERNAME VARCHAR(128) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		CREATIONTIMESTAMP TIMESTAMP NOT NULL,
		EVENT CHAR(1) NOT NULL,
		FIRINGTIME CHAR(1) NOT NULL,
		TYPE CHAR(1) NOT NULL,
		STATE CHAR(1) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		WHENSTMTID CHAR(36),
		ACTIONSTMTID CHAR(36),
		REFERENCEDCOLUMNS null,
		TRIGGERDEFINITION LONG VARCHAR,
		REFERENCINGOLD null,
		REFERENCINGNEW null,
		OLDREFERENCINGNAME VARCHAR(128),
		NEWREFERENCINGNAME VARCHAR(128),
		WHENCLAUSETEXT LONG VARCHAR
	);

CREATE TABLE SYS.SYSVIEWS (
		TABLEID CHAR(36) NOT NULL,
		VIEWDEFINITION LONG VARCHAR NOT NULL,
		CHECKOPTION CHAR(1) NOT NULL,
		COMPILATIONSCHEMAID CHAR(36)
	);

CREATE TABLE SYS.SYSCOLPERMS (
		COLPERMSID CHAR(36) NOT NULL,
		GRANTEE VARCHAR(128) NOT NULL,
		GRANTOR VARCHAR(128) NOT NULL,
		TABLEID CHAR(36) NOT NULL,
		TYPE CHAR(1) NOT NULL,
		COLUMNS null NOT NULL
	);

CREATE TABLE SYS.SYSPERMS (
		UUID CHAR(36) NOT NULL,
		OBJECTTYPE VARCHAR(36) NOT NULL,
		OBJECTID CHAR(36) NOT NULL,
		PERMISSION CHAR(36) NOT NULL,
		GRANTOR VARCHAR(128) NOT NULL,
		GRANTEE VARCHAR(128) NOT NULL,
		ISGRANTABLE CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSSEQUENCES (
		SEQUENCEID CHAR(36) NOT NULL,
		SEQUENCENAME VARCHAR(128) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		SEQUENCEDATATYPE null NOT NULL,
		CURRENTVALUE BIGINT,
		STARTVALUE BIGINT NOT NULL,
		MINIMUMVALUE BIGINT NOT NULL,
		MAXIMUMVALUE BIGINT NOT NULL,
		INCREMENT BIGINT NOT NULL,
		CYCLEOPTION CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSFOREIGNKEYS (
		CONSTRAINTID CHAR(36) NOT NULL,
		CONGLOMERATEID CHAR(36) NOT NULL,
		KEYCONSTRAINTID CHAR(36) NOT NULL,
		DELETERULE CHAR(1) NOT NULL,
		UPDATERULE CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSFILES (
		FILEID CHAR(36) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		FILENAME VARCHAR(128) NOT NULL,
		GENERATIONID BIGINT NOT NULL
	);

CREATE TABLE SYS.SYSSTATEMENTS (
		STMTID CHAR(36) NOT NULL,
		STMTNAME VARCHAR(128) NOT NULL,
		SCHEMAID CHAR(36) NOT NULL,
		TYPE CHAR(1) NOT NULL,
		VALID null NOT NULL,
		TEXT LONG VARCHAR NOT NULL,
		LASTCOMPILED TIMESTAMP,
		COMPILATIONSCHEMAID CHAR(36),
		USINGTEXT LONG VARCHAR
	);

CREATE TABLE SYS.SYSKEYS (
		CONSTRAINTID CHAR(36) NOT NULL,
		CONGLOMERATEID CHAR(36) NOT NULL
	);

CREATE TABLE SYSIBM.SYSDUMMY1 (
		IBMREQD CHAR(1)
	);

CREATE TABLE SYS.SYSALIASES (
		ALIASID CHAR(36) NOT NULL,
		ALIAS VARCHAR(128) NOT NULL,
		SCHEMAID CHAR(36),
		JAVACLASSNAME LONG VARCHAR NOT NULL,
		ALIASTYPE CHAR(1) NOT NULL,
		NAMESPACE CHAR(1) NOT NULL,
		SYSTEMALIAS null NOT NULL,
		ALIASINFO null,
		SPECIFICNAME VARCHAR(128) NOT NULL
	);

CREATE TABLE SYS.SYSROLES (
		UUID CHAR(36) NOT NULL,
		ROLEID VARCHAR(128) NOT NULL,
		GRANTEE VARCHAR(128) NOT NULL,
		GRANTOR VARCHAR(128) NOT NULL,
		WITHADMINOPTION CHAR(1) NOT NULL,
		ISDEF CHAR(1) NOT NULL
	);

CREATE TABLE SYS.SYSCHECKS (
		CONSTRAINTID CHAR(36) NOT NULL,
		CHECKDEFINITION LONG VARCHAR NOT NULL,
		REFERENCEDCOLUMNS null NOT NULL
	);
