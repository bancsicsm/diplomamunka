function res = debug(debug_level)

global DEBUG_LEVEL

res = logical(DEBUG_LEVEL >= debug_level);

