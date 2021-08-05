# dmx-classification-systems

A repository for research with/on international statistic classification systems.

The goal of this work is to replace individual concordance matrices (Spreadsheets) as used in e.g. Industrial Ecology and MRIO-Analysis with

A) a much more expressive and fitting associative data model for linking codes from different statistical classification standards,<br/>
B) doing so in FAIR and collaborative manner to avoid repeating these tasks on an individual (and error-prone) level, and
C) engage researchers to comprehend, discuss and reproduce research involving the mapping of two (or more) codes from international statistic classification standards.


## Issues

Issues are tracked on this repositories public mirror at [github.com](https://github.com/mukil/dmx-classifications) for world wide read/write access.

## Release History

**1.0.1**, Upcoming

Improvements for using this plugin in a multi-user installation:<br/>
* Introduced a confidential `Classification Systems` workspace<br/>
* Moved all top-level topic types into the new _Confidential_ workspace 
* Adapted to be compatible with DMX 5.2 platform
* Renamed "Category" to "Classification Category" to not interfere with other namespaces
* Renamed all association types to not interere with indicator-sets plugin

**1.0.0**, Dec 17, 2019

* Introduces Application Model:<br/>
  Topic Types: _Classification_, _Classification System_, _Category_<br/>
  Assoc Types: _Equivalence_, _Similar_, _Defines_, _Contains_, _Categorizes_<br/>
* Compatible with DMX 5.0-beta-6

## Author

Copyright (C) Malte Reißig, 2019-2021

## Acknowledgement

This work is funded by the German Federal Ministry of Education and Research as part of its funding initiative “Social-Ecological Research“ and the Junior Research Group “ProMUT” (grant number: 01UU1705A).
