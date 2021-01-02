
# Manufacturing Process Management System (MPMS)


### Purpose

MPMS provides end-to-end (i.e., from order reception until product delivery) manufacturing process management and orchestration of activities by:

- modeling processes and agents
- executing in automated way the processes by assigning activities to agents
- providing process monitoring for a complete status overview of the manufacturing processes

# MPMS for SHOP4CF

A Process Application for a Manufacturing Process Management System for [SHOP4CF](https://www.shop4cf.eu/) project with [Camunda BPM](https://camunda.com/).
Developed by [Information Systems research group](https://www.tue.nl/en/research/research-groups/information-systems-ieis/) of [TUe](https://www.tue.nl/en/).

(This project has been generated by the Maven archetype
[camunda-archetype-ejb-war-7.8.0](https://docs.camunda.org/manual/7.8/user-guide/process-applications/maven-archetypes/)).

## How to use it?

**Deploy the application process:**\
Copy the generated .war file  from **`target`** folder into
>_**$CAMUNDA_HOME**_\camunda-bpm-wildfly10-7.8.0\server\wildfly-10.1.0.Final\standalone\deployments

**Run the application server:**\
Run **`start-camunda.bat`** file (or **`start-camunda.sh`** for Linux users) under your downloaded folder
>_**$CAMUNDA_HOME**_\camunda-bpm-wildfly10-7.8.0

**Access the web applications on:**\
http://[host_address]:[port]/camunda/app/welcome/default/#/login

**Run Processes and view Tasks in [Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist).**

**Monitor processes in [Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).**

**Stop the application server by:**\
Navigate to
> *$CAMUNDA_HOME\server\wildfly-10.1.0.Final\bin*

and open a **cmd** console under this path. Run the following command (Windows or Linux users respectively):
> jboss-cli.bat --connect controller=127.0.0.1:[**9990+port_offset**] command=:**shutdown**

> jboss-cli.sh --connect controller=127.0.0.1:[**9990+port_offset**] command=:**shutdown**
>
## Environment Restrictions
Built and tested against Camunda BPM version 7.8.0, Wildfly10 distribution.

## Known Limitations

## Improvements Backlog

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Version History

- **Version 1.0** (30-Nov-2020)

  Initial version of MPMS with:
	- Core functionality
		- DB integration
		- Websocket (MessageBus) integration
		- Integration to HTS component
		- Task alerts