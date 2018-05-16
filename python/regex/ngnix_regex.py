IP='(?<![0-9])(?:(?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2})[.](?:25[0-5]|2[0-4][0-9]|[0-1]?[0-9]{1,2}))(?![0-9])'
HOSTNAME=r'\b(?:[0-9A-Za-z][0-9A-Za-z-]{0,62})(?:\.(?:[0-9A-Za-z][0-9A-Za-z-]{0,62}))*(\.?|\b)'
IPORHOST='(?:%s|%s)'%(HOSTNAME,IP)

USERNAME='[a-zA-Z0-9_-]+'
USER='%s'%USERNAME
INT='(?:[+-]?(?:[0-9]+))'
POSINT=r'(?:[1-9][0-9]*)'
BASE10NUM='(?<![0-9.+-])(?>[+-]?(?:(?:[0-9]+(?:\.[0-9]+)?)|(?:\.[0-9]+)))'
NUMBER='(?:%s)'%BASE10NUM
BASE16FLOAT=r'\b(?<![0-9A-Fa-f.])(?:[+-]?(?:0x)?(?:(?:[0-9A-Fa-f]+(?:\.[0-9A-Fa-f]*)?)|(?:\.[0-9A-Fa-f]+)))\b'
FLOAT='[-+]?(\d*[.])?\d+'

WORD='\w+'


# Months: January, Feb, 3, 03, 12, December
MONTH=r'(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)'
MONTHNUM='(?:0?[1-9]|1[0-2])'
MONTHDAY='(?:(?:0[1-9])|(?:[12][0-9])|(?:3[01])|[1-9])'
YEAR='[0-9]+' 
HOUR='(?:2[0123]|[01][0-9])'
MINUTE='(?:[0-5][0-9])'
# '60' is a leap second in most time standards and thus is valid.
SECOND='(?:(?:[0-5][0-9]|60)(?:[.,][0-9]+)?)'
TIME='(?!<[0-9])%s:%s(?::%s)(?![0-9])'%(HOUR, MINUTE, SECOND)
ISO8601_TIMEZONE='(?:Z|[+-]%s(?::?%s))'%(HOUR, MINUTE)
ZONE=ISO8601_TIMEZONE
HTTPDATE='%s/%s/%s:%s\ %s'%(MONTHDAY, MONTH, YEAR, TIME, INT)


# paths
UNIXPATH='(?:/(?:[\w_%!$@:.,-]+|\\.)*)+'
WINPATH='(?:[A-Za-z]+:|\\)(?:\\[^\\?*]*)+'
URIPROTO='[A-Za-z]+(\+[A-Za-z+]+)?'
PATH='(?:%s|%s)'%(UNIXPATH, WINPATH)
URIHOST='%s(?::{%s:port})?'%(IPORHOST, POSINT)
# uripath comes loosely from RFC1738, but mostly from what Firefox
# doesn't turn into %XX
URIPATH=r"(?:/[A-Za-z0-9$.+!*'(){},~:;=#%_-]*)+"
#URIPARAM \?(?:[A-Za-z0-9]+(?:=(?:[^&]*))?(?:&(?:[A-Za-z0-9]+(?:=(?:[^&]*))?)?)*)?
URIPARAM=r"\?[A-Za-z0-9$.+!*'|(){},~#%&/=:;_-]*"
URIPATHPARAM='%s(?:%s)?'%(URIPATH, URIPARAM)
URI='%s://(?:%s(?::[^@]*)?@)?(?:%s)?(?:%s)?'%(URIPROTO, USER, URIHOST, URIPATHPARAM)
REQUEST='%s\ %s\ %s/%s'%(WORD, URIPATHPARAM, URIPROTO, FLOAT)
AGENT='.*'
SERVERNAME='.*'
