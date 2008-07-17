CREATE TABLE JBPM_ACTION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                         ,
    class CHAR(1) NOT NULL        ,
    NAME_ VARCHAR(255)            ,
    ISPROPAGATIONALLOWED_ bit     ,
    ACTIONEXPRESSION_ VARCHAR(255),
    ISASYNC_ bit                  ,
    REFERENCEDACTION_ bigint      ,
    ACTIONDELEGATION_ bigint      ,
    EVENT_ bigint                 ,
    PROCESSDEFINITION_ bigint     ,
    TIMERNAME_      VARCHAR(255)       ,
    DUEDATE_        VARCHAR(255)       ,
    REPEAT_         VARCHAR(255)       ,
    TRANSITIONNAME_ VARCHAR(255)       ,
    TIMERACTION_ bigint                ,
    EXPRESSION_ VARCHAR(4000)          ,
    EVENTINDEX_ INTEGER                ,
    EXCEPTIONHANDLER_ bigint           ,
    EXCEPTIONHANDLERINDEX_ INTEGER     ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_BYTEARRAY (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                 ,
    NAME_ VARCHAR(255)    ,
    FILEDEFINITION_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_BYTEBLOCK (
    PROCESSFILE_ bigint NOT NULL,
    BYTES_ VARBINARY(1024)      ,
    INDEX_ INTEGER NOT NULL     ,
    PRIMARY KEY (PROCESSFILE_, INDEX_)
  );

CREATE TABLE JBPM_COMMENT (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                    ,
    VERSION_ INTEGER NOT NULL,
    ACTORID_ VARCHAR(255)    ,
    TIME_ TIMESTAMP          ,
    MESSAGE_ VARCHAR(4000)   ,
    TOKEN_ bigint            ,
    TASKINSTANCE_ bigint     ,
    TOKENINDEX_        INTEGER      ,
    TASKINSTANCEINDEX_ INTEGER      ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_DECISIONCONDITIONS (
    DECISION_ bigint NOT NULL       ,
    TRANSITIONNAME_ VARCHAR(255)    ,
    EXPRESSION_     VARCHAR(255)    ,
    INDEX_          INTEGER NOT NULL,
    PRIMARY KEY (DECISION_, INDEX_)
  );

CREATE TABLE JBPM_DELEGATION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                   ,
    CLASSNAME_     VARCHAR(4000),
    CONFIGURATION_ VARCHAR(4000),
    CONFIGTYPE_    VARCHAR(255) ,
    PROCESSDEFINITION_ bigint   ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_EVENT (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                    ,
    EVENTTYPE_ VARCHAR(255)  ,
    TYPE_      CHAR(1)       ,
    GRAPHELEMENT_ bigint     ,
    PROCESSDEFINITION_ bigint,
    NODE_ bigint             ,
    TRANSITION_ bigint       ,
    TASK_ bigint             ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_EXCEPTIONHANDLER (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                            ,
    EXCEPTIONCLASSNAME_ VARCHAR(4000),
    TYPE_               CHAR(1)      ,
    GRAPHELEMENT_ bigint             ,
    PROCESSDEFINITION_ bigint        ,
    GRAPHELEMENTINDEX_ INTEGER       ,
    NODE_ bigint                     ,
    TRANSITION_ bigint               ,
    TASK_ bigint                     ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_ID_GROUP (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_ CHAR(1) NOT NULL,
    NAME_  VARCHAR(255)    ,
    TYPE_  VARCHAR(255)    ,
    PARENT_ bigint         ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_ID_MEMBERSHIP (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_ CHAR(1) NOT NULL,
    NAME_  VARCHAR(255)    ,
    ROLE_  VARCHAR(255)    ,
    USER_ bigint           ,
    GROUP_ bigint          ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_ID_PERMISSIONS (
    ENTITY_ bigint NOT NULL,
    CLASS_  VARCHAR(255)    ,
    NAME_   VARCHAR(255)    ,
    ACTION_ VARCHAR(255)
  );

CREATE TABLE JBPM_ID_USER (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_    CHAR(1) NOT NULL,
    NAME_     VARCHAR(255)    ,
    EMAIL_    VARCHAR(255)    ,
    PASSWORD_ VARCHAR(255)    ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_LOG (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                         ,
    CLASS_ CHAR(1) NOT NULL       ,
    INDEX_ INTEGER                ,
    DATE_ TIMESTAMP               ,
    TOKEN_ bigint                 ,
    PARENT_ bigint                ,
    MESSAGE_   VARCHAR(4000)      ,
    EXCEPTION_ VARCHAR(4000)      ,
    ACTION_ bigint                ,
    NODE_ bigint                  ,
    ENTER_ TIMESTAMP              ,
    LEAVE_ TIMESTAMP              ,
    DURATION_ bigint              ,
    NEWLONGVALUE_ bigint          ,
    TRANSITION_ bigint            ,
    CHILD_ bigint                 ,
    SOURCENODE_ bigint            ,
    DESTINATIONNODE_ bigint       ,
    VARIABLEINSTANCE_ bigint      ,
    OLDBYTEARRAY_ bigint          ,
    NEWBYTEARRAY_ bigint          ,
    OLDDATEVALUE_ TIMESTAMP       ,
    NEWDATEVALUE_ TIMESTAMP       ,
    OLDDOUBLEVALUE_ DOUBLE        ,
    NEWDOUBLEVALUE_ DOUBLE        ,
    OLDLONGIDCLASS_ VARCHAR(255)  ,
    OLDLONGIDVALUE_ bigint        ,
    NEWLONGIDCLASS_ VARCHAR(255)  ,
    NEWLONGIDVALUE_ bigint        ,
    OLDSTRINGIDCLASS_ VARCHAR(255),
    OLDSTRINGIDVALUE_ VARCHAR(255),
    NEWSTRINGIDCLASS_ VARCHAR(255),
    NEWSTRINGIDVALUE_ VARCHAR(255),
    OLDLONGVALUE_ bigint          ,
    OLDSTRINGVALUE_ VARCHAR(4000) ,
    NEWSTRINGVALUE_ VARCHAR(4000) ,
    TASKINSTANCE_ bigint          ,
    TASKACTORID_    VARCHAR(255)     ,
    TASKOLDACTORID_ VARCHAR(255)     ,
    SWIMLANEINSTANCE_ bigint         ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_MESSAGE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_       CHAR(1) NOT NULL,
    DESTINATION_ VARCHAR(255)    ,
    EXCEPTION_   VARCHAR(4000)   ,
    ISSUSPENDED_ bit             ,
    TOKEN_ bigint                ,
    TEXT_ VARCHAR(255)           ,
    ACTION_ bigint               ,
    NODE_ bigint                 ,
    TRANSITIONNAME_ VARCHAR(255) ,
    TASKINSTANCE_ bigint         ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_MODULEDEFINITION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                    ,
    CLASS_ CHAR(1) NOT NULL  ,
    NAME_  VARCHAR(4000)     ,
    PROCESSDEFINITION_ bigint,
    STARTTASK_ bigint        ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_MODULEINSTANCE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                     ,
    CLASS_ CHAR(1) NOT NULL   ,
    PROCESSINSTANCE_ bigint   ,
    TASKMGMTDEFINITION_ bigint,
    NAME_ VARCHAR(255)        ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_NODE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                           ,
    CLASS_ CHAR(1) NOT NULL         ,
    NAME_  VARCHAR(255)             ,
    PROCESSDEFINITION_ bigint       ,
    ISASYNC_ bit                    ,
    ACTION_ bigint                  ,
    SUPERSTATE_ bigint              ,
    SUBPROCESSDEFINITION_ bigint    ,
    DECISIONEXPRESSION_ VARCHAR(255),
    DECISIONDELEGATION bigint       ,
    SIGNAL_ INTEGER                 ,
    CREATETASKS_ bit                ,
    ENDTASKS_ bit                   ,
    NODECOLLECTIONINDEX_ INTEGER    ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_POOLEDACTOR (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                   ,
    ACTORID_ VARCHAR(255)   ,
    SWIMLANEINSTANCE_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_PROCESSDEFINITION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                     ,
    NAME_    VARCHAR(255)     ,
    VERSION_ INTEGER          ,
    ISTERMINATIONIMPLICIT_ bit,
    STARTSTATE_ bigint        ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_PROCESSINSTANCE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                    ,
    VERSION_ INTEGER NOT NULL,
    START_ TIMESTAMP         ,
    END_ TIMESTAMP           ,
    ISSUSPENDED_ bit         ,
    PROCESSDEFINITION_ bigint,
    ROOTTOKEN_ bigint        ,
    SUPERPROCESSTOKEN_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_RUNTIMEACTION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                        ,
    VERSION_   INTEGER NOT NULL  ,
    EVENTTYPE_ VARCHAR(255)      ,
    TYPE_      CHAR(1)           ,
    GRAPHELEMENT_ bigint         ,
    PROCESSINSTANCE_ bigint      ,
    ACTION_ bigint               ,
    PROCESSINSTANCEINDEX_ INTEGER,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_SWIMLANE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)             ,
    NAME_                   VARCHAR(255),
    ACTORIDEXPRESSION_      VARCHAR(255),
    POOLEDACTORSEXPRESSION_ VARCHAR(255),
    ASSIGNMENTDELEGATION_ bigint        ,
    TASKMGMTDEFINITION_ bigint          ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_SWIMLANEINSTANCE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                   ,
    NAME_    VARCHAR(255)   ,
    ACTORID_ VARCHAR(255)   ,
    SWIMLANE_ bigint        ,
    TASKMGMTINSTANCE_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TASK (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                     ,
    NAME_ VARCHAR(255)        ,
    PROCESSDEFINITION_ bigint ,
    DESCRIPTION_ VARCHAR(4000),
    ISBLOCKING_ bit           ,
    ISSIGNALLING_ bit         ,
    DUEDATE_                VARCHAR(255)     ,
    ACTORIDEXPRESSION_      VARCHAR(255)     ,
    POOLEDACTORSEXPRESSION_ VARCHAR(255)     ,
    TASKMGMTDEFINITION_ bigint               ,
    TASKNODE_ bigint                         ,
    STARTSTATE_ bigint                       ,
    ASSIGNMENTDELEGATION_ bigint             ,
    SWIMLANE_ bigint                         ,
    TASKCONTROLLER_ bigint                   ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TASKACTORPOOL (
    TASKINSTANCE_ bigint NOT NULL,
    POOLEDACTOR_ bigint NOT NULL ,
    PRIMARY KEY (TASKINSTANCE_, POOLEDACTOR_)
  );

CREATE TABLE JBPM_TASKCONTROLLER (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                           ,
    TASKCONTROLLERDELEGATION_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TASKINSTANCE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_       CHAR(1) NOT NULL,
    NAME_        VARCHAR(255)    ,
    DESCRIPTION_ VARCHAR(4000)   ,
    ACTORID_     VARCHAR(255)    ,
    CREATE_ TIMESTAMP            ,
    START_ TIMESTAMP             ,
    END_ TIMESTAMP               ,
    DUEDATE_ TIMESTAMP           ,
    PRIORITY_ INTEGER            ,
    ISCANCELLED_ bit             ,
    ISSUSPENDED_ bit             ,
    ISOPEN_ bit                  ,
    ISSIGNALLING_ bit            ,
    ISBLOCKING_ bit              ,
    TASK_ bigint                 ,
    TOKEN_ bigint                ,
    SWIMLANINSTANCE_ bigint      ,
    TASKMGMTINSTANCE_ bigint     ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TIMER (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                         ,
    NAME_ VARCHAR(255)            ,
    DUEDATE_ TIMESTAMP            ,
    REPEAT_         VARCHAR(255)  ,
    TRANSITIONNAME_ VARCHAR(255)  ,
    EXCEPTION_      VARCHAR(4000) ,
    ISSUSPENDED_ bit              ,
    ACTION_ bigint                ,
    TOKEN_ bigint                 ,
    PROCESSINSTANCE_ bigint       ,
    TASKINSTANCE_ bigint          ,
    GRAPHELEMENTTYPE_ VARCHAR(255),
    GRAPHELEMENT_ bigint          ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TOKEN (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                        ,
    VERSION_ INTEGER NOT NULL    ,
    NAME_    VARCHAR(255)        ,
    START_ TIMESTAMP             ,
    END_ TIMESTAMP               ,
    NODEENTER_ TIMESTAMP         ,
    NEXTLOGINDEX_ INTEGER        ,
    ISABLETOREACTIVATEPARENT_ bit,
    ISTERMINATIONIMPLICIT_ bit   ,
    ISSUSPENDED_ bit             ,
    NODE_ bigint                 ,
    PROCESSINSTANCE_ bigint      ,
    PARENT_ bigint               ,
    SUBPROCESSINSTANCE_ bigint   ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TOKENVARIABLEMAP (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    TOKEN_ bigint          ,
    CONTEXTINSTANCE_ bigint,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_TRANSITION (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                    ,
    NAME_ VARCHAR(255)       ,
    PROCESSDEFINITION_ bigint,
    FROM_ bigint             ,
    TO_ bigint               ,
    FROMINDEX_ INTEGER       ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_VARIABLEACCESS (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                     ,
    VARIABLENAME_ VARCHAR(255),
    ACCESS_       VARCHAR(255),
    MAPPEDNAME_   VARCHAR(255),
    PROCESSSTATE_ bigint      ,
    TASKCONTROLLER_ bigint    ,
    INDEX_ INTEGER            ,
    SCRIPT_ bigint            ,
    PRIMARY KEY (ID_)
  );

CREATE TABLE JBPM_VARIABLEINSTANCE (
    ID_ bigint generated BY DEFAULT AS identity (START
  WITH 1)                  ,
    CLASS_     CHAR(1) NOT NULL,
    NAME_      VARCHAR(255)    ,
    CONVERTER_ CHAR(1)         ,
    TOKEN_ bigint              ,
    TOKENVARIABLEMAP_ bigint   ,
    PROCESSINSTANCE_ bigint    ,
    BYTEARRAYVALUE_ bigint     ,
    DATEVALUE_ TIMESTAMP       ,
    DOUBLEVALUE_ DOUBLE        ,
    LONGIDCLASS_ VARCHAR(255)  ,
    LONGVALUE_ bigint          ,
    STRINGIDCLASS_ VARCHAR(255),
    STRINGVALUE_   VARCHAR(255),
    TASKINSTANCE_ bigint       ,
    PRIMARY KEY (ID_)
  );

ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_ACTION_EVENT FOREIGN KEY(
  EVENT_
)
REFERENCES JBPM_EVENT;
ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_ACTION_EXPTHDL FOREIGN KEY(
  EXCEPTIONHANDLER_
)
REFERENCES JBPM_EXCEPTIONHANDLER;
ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_ACTION_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_CRTETIMERACT_TA FOREIGN KEY(
  TIMERACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_ACTION_ACTNDEL FOREIGN KEY(
  ACTIONDELEGATION_
)
REFERENCES JBPM_DELEGATION;
ALTER TABLE JBPM_ACTION ADD CONSTRAINT FK_ACTION_REFACT FOREIGN KEY(
  REFERENCEDACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_BYTEARRAY ADD CONSTRAINT FK_BYTEARR_FILDEF FOREIGN KEY(
  FILEDEFINITION_
)
REFERENCES JBPM_MODULEDEFINITION;
ALTER TABLE JBPM_BYTEBLOCK ADD CONSTRAINT FK_BYTEBLOCK_FILE FOREIGN KEY(
  PROCESSFILE_
)
REFERENCES JBPM_BYTEARRAY;
ALTER TABLE JBPM_COMMENT ADD CONSTRAINT FK_COMMENT_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_COMMENT ADD CONSTRAINT FK_COMMENT_TSK FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_DECISIONCONDITIONS ADD CONSTRAINT FK_DECCOND_DEC FOREIGN KEY(
  DECISION_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_DELEGATION ADD CONSTRAINT FK_DELEGATION_PRCD FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_EVENT ADD CONSTRAINT FK_EVENT_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_EVENT ADD CONSTRAINT FK_EVENT_NODE FOREIGN KEY(
  NODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_EVENT ADD CONSTRAINT FK_EVENT_TRANS FOREIGN KEY(
  TRANSITION_
)
REFERENCES JBPM_TRANSITION;
ALTER TABLE JBPM_EVENT ADD CONSTRAINT FK_EVENT_TASK FOREIGN KEY(
  TASK_
)
REFERENCES JBPM_TASK;
ALTER TABLE JBPM_ID_GROUP ADD CONSTRAINT FK_ID_GRP_PARENT FOREIGN KEY(
  PARENT_
)
REFERENCES JBPM_ID_GROUP;
ALTER TABLE JBPM_ID_MEMBERSHIP ADD CONSTRAINT FK_ID_MEMSHIP_GRP FOREIGN KEY(
  GROUP_
)
REFERENCES JBPM_ID_GROUP;
ALTER TABLE JBPM_ID_MEMBERSHIP ADD CONSTRAINT FK_ID_MEMSHIP_USR FOREIGN KEY(
  USER_
)
REFERENCES JBPM_ID_USER;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_SOURCENODE FOREIGN KEY(
  SOURCENODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_OLDBYTES FOREIGN KEY(
  OLDBYTEARRAY_
)
REFERENCES JBPM_BYTEARRAY;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_NEWBYTES FOREIGN KEY(
  NEWBYTEARRAY_
)
REFERENCES JBPM_BYTEARRAY;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_CHILDTOKEN FOREIGN KEY(
  CHILD_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_DESTNODE FOREIGN KEY(
  DESTINATIONNODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_TASKINST FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_SWIMINST FOREIGN KEY(
  SWIMLANEINSTANCE_
)
REFERENCES JBPM_SWIMLANEINSTANCE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_PARENT FOREIGN KEY(
  PARENT_
)
REFERENCES JBPM_LOG;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_NODE FOREIGN KEY(
  NODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_ACTION FOREIGN KEY(
  ACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_VARINST FOREIGN KEY(
  VARIABLEINSTANCE_
)
REFERENCES JBPM_VARIABLEINSTANCE;
ALTER TABLE JBPM_LOG ADD CONSTRAINT FK_LOG_TRANSITION FOREIGN KEY(
  TRANSITION_
)
REFERENCES JBPM_TRANSITION;
ALTER TABLE JBPM_MESSAGE ADD CONSTRAINT FK_MSG_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_MESSAGE ADD CONSTRAINT FK_CMD_NODE FOREIGN KEY(
  NODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_MESSAGE ADD CONSTRAINT FK_CMD_ACTION FOREIGN KEY(
  ACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_MESSAGE ADD CONSTRAINT FK_CMD_TASKINST FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_MODULEDEFINITION ADD CONSTRAINT FK_TSKDEF_START FOREIGN KEY(
  STARTTASK_
)
REFERENCES JBPM_TASK;
ALTER TABLE JBPM_MODULEDEFINITION ADD CONSTRAINT FK_MODDEF_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_MODULEINSTANCE ADD CONSTRAINT FK_TASKMGTINST_TMD FOREIGN KEY(
  TASKMGMTDEFINITION_
)
REFERENCES JBPM_MODULEDEFINITION;
ALTER TABLE JBPM_MODULEINSTANCE ADD CONSTRAINT FK_MODINST_PRCINST FOREIGN KEY(
  PROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_NODE ADD CONSTRAINT FK_PROCST_SBPRCDEF FOREIGN KEY(
  SUBPROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_NODE ADD CONSTRAINT FK_NODE_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_NODE ADD CONSTRAINT FK_NODE_ACTION FOREIGN KEY(
  ACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_NODE ADD CONSTRAINT FK_DECISION_DELEG FOREIGN KEY(
  DECISIONDELEGATION
)
REFERENCES JBPM_DELEGATION;
ALTER TABLE JBPM_NODE ADD CONSTRAINT FK_NODE_SUPERSTATE FOREIGN KEY(
  SUPERSTATE_
)
REFERENCES JBPM_NODE;
CREATE INDEX IDX_PLDACTR_ACTID ON JBPM_POOLEDACTOR(
    ACTORID_
  );

ALTER TABLE JBPM_POOLEDACTOR ADD CONSTRAINT FK_POOLEDACTOR_SLI FOREIGN KEY(
  SWIMLANEINSTANCE_
)
REFERENCES JBPM_SWIMLANEINSTANCE;
ALTER TABLE JBPM_PROCESSDEFINITION ADD CONSTRAINT FK_PROCDEF_STRTSTA FOREIGN KEY(
  STARTSTATE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_PROCESSINSTANCE ADD CONSTRAINT FK_PROCIN_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_PROCESSINSTANCE ADD CONSTRAINT FK_PROCIN_ROOTTKN FOREIGN KEY(
  ROOTTOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_PROCESSINSTANCE ADD CONSTRAINT FK_PROCIN_SPROCTKN FOREIGN KEY(
  SUPERPROCESSTOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_RUNTIMEACTION ADD CONSTRAINT FK_RTACTN_PROCINST FOREIGN KEY(
  PROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_RUNTIMEACTION ADD CONSTRAINT FK_RTACTN_ACTION FOREIGN KEY(
  ACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_SWIMLANE ADD CONSTRAINT FK_SWL_ASSDEL FOREIGN KEY(
  ASSIGNMENTDELEGATION_
)
REFERENCES JBPM_DELEGATION;
ALTER TABLE JBPM_SWIMLANE ADD CONSTRAINT FK_SWL_TSKMGMTDEF FOREIGN KEY(
  TASKMGMTDEFINITION_
)
REFERENCES JBPM_MODULEDEFINITION;
ALTER TABLE JBPM_SWIMLANEINSTANCE ADD CONSTRAINT FK_SWIMLANEINST_TM FOREIGN KEY(
  TASKMGMTINSTANCE_
)
REFERENCES JBPM_MODULEINSTANCE;
ALTER TABLE JBPM_SWIMLANEINSTANCE ADD CONSTRAINT FK_SWIMLANEINST_SL FOREIGN KEY(
  SWIMLANE_
)
REFERENCES JBPM_SWIMLANE;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TSK_TSKCTRL FOREIGN KEY(
  TASKCONTROLLER_
)
REFERENCES JBPM_TASKCONTROLLER;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_ASSDEL FOREIGN KEY(
  ASSIGNMENTDELEGATION_
)
REFERENCES JBPM_DELEGATION;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_TASKNODE FOREIGN KEY(
  TASKNODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_STARTST FOREIGN KEY(
  STARTSTATE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_TASKMGTDEF FOREIGN KEY(
  TASKMGMTDEFINITION_
)
REFERENCES JBPM_MODULEDEFINITION;
ALTER TABLE JBPM_TASK ADD CONSTRAINT FK_TASK_SWIMLANE FOREIGN KEY(
  SWIMLANE_
)
REFERENCES JBPM_SWIMLANE;
ALTER TABLE JBPM_TASKACTORPOOL ADD CONSTRAINT FK_TSKACTPOL_PLACT FOREIGN KEY(
  POOLEDACTOR_
)
REFERENCES JBPM_POOLEDACTOR;
ALTER TABLE JBPM_TASKACTORPOOL ADD CONSTRAINT FK_TASKACTPL_TSKI FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_TASKCONTROLLER ADD CONSTRAINT FK_TSKCTRL_DELEG FOREIGN KEY(
  TASKCONTROLLERDELEGATION_
)
REFERENCES JBPM_DELEGATION;
CREATE INDEX IDX_TASK_ACTORID ON JBPM_TASKINSTANCE
  (
    ACTORID_
  );

ALTER TABLE JBPM_TASKINSTANCE ADD CONSTRAINT FK_TASKINST_TMINST FOREIGN KEY(
  TASKMGMTINSTANCE_
)
REFERENCES JBPM_MODULEINSTANCE;
ALTER TABLE JBPM_TASKINSTANCE ADD CONSTRAINT FK_TASKINST_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_TASKINSTANCE ADD CONSTRAINT FK_TASKINST_SLINST FOREIGN KEY(
  SWIMLANINSTANCE_
)
REFERENCES JBPM_SWIMLANEINSTANCE;
ALTER TABLE JBPM_TASKINSTANCE ADD CONSTRAINT FK_TASKINST_TASK FOREIGN KEY(
  TASK_
)
REFERENCES JBPM_TASK;
ALTER TABLE JBPM_TIMER ADD CONSTRAINT FK_TIMER_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_TIMER ADD CONSTRAINT FK_TIMER_PRINST FOREIGN KEY(
  PROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_TIMER ADD CONSTRAINT FK_TIMER_ACTION FOREIGN KEY(
  ACTION_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_TIMER ADD CONSTRAINT FK_TIMER_TSKINST FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_TOKEN ADD CONSTRAINT FK_TOKEN_PARENT FOREIGN KEY(
  PARENT_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_TOKEN ADD CONSTRAINT FK_TOKEN_NODE FOREIGN KEY(
  NODE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_TOKEN ADD CONSTRAINT FK_TOKEN_PROCINST FOREIGN KEY(
  PROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_TOKEN ADD CONSTRAINT FK_TOKEN_SUBPI FOREIGN KEY(
  SUBPROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_TOKENVARIABLEMAP ADD CONSTRAINT FK_TKVARMAP_CTXT FOREIGN KEY(
  CONTEXTINSTANCE_
)
REFERENCES JBPM_MODULEINSTANCE;
ALTER TABLE JBPM_TOKENVARIABLEMAP ADD CONSTRAINT FK_TKVARMAP_TOKEN FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_TRANSITION ADD CONSTRAINT FK_TRANSITION_TO FOREIGN KEY(
  TO_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_TRANSITION ADD CONSTRAINT FK_TRANS_PROCDEF FOREIGN KEY(
  PROCESSDEFINITION_
)
REFERENCES JBPM_PROCESSDEFINITION;
ALTER TABLE JBPM_TRANSITION ADD CONSTRAINT FK_TRANSITION_FROM FOREIGN KEY(
  FROM_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_VARIABLEACCESS ADD CONSTRAINT FK_VARACC_TSKCTRL FOREIGN KEY(
  TASKCONTROLLER_
)
REFERENCES JBPM_TASKCONTROLLER;
ALTER TABLE JBPM_VARIABLEACCESS ADD CONSTRAINT FK_VARACC_SCRIPT FOREIGN KEY(
  SCRIPT_
)
REFERENCES JBPM_ACTION;
ALTER TABLE JBPM_VARIABLEACCESS ADD CONSTRAINT FK_VARACC_PROCST FOREIGN KEY(
  PROCESSSTATE_
)
REFERENCES JBPM_NODE;
ALTER TABLE JBPM_VARIABLEINSTANCE ADD CONSTRAINT FK_VARINST_TK FOREIGN KEY(
  TOKEN_
)
REFERENCES JBPM_TOKEN;
ALTER TABLE JBPM_VARIABLEINSTANCE ADD CONSTRAINT FK_VARINST_TKVARMP FOREIGN KEY(
  TOKENVARIABLEMAP_
)
REFERENCES JBPM_TOKENVARIABLEMAP;
ALTER TABLE JBPM_VARIABLEINSTANCE ADD CONSTRAINT FK_VARINST_PRCINST FOREIGN KEY(
  PROCESSINSTANCE_
)
REFERENCES JBPM_PROCESSINSTANCE;
ALTER TABLE JBPM_VARIABLEINSTANCE ADD CONSTRAINT FK_VAR_TSKINST FOREIGN KEY(
  TASKINSTANCE_
)
REFERENCES JBPM_TASKINSTANCE;
ALTER TABLE JBPM_VARIABLEINSTANCE ADD CONSTRAINT FK_BYTEINST_ARRAY FOREIGN KEY(
  BYTEARRAYVALUE_
)
REFERENCES JBPM_BYTEARRAY;