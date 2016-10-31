#pragma once

#include "../common/note.h"
#include "CompositionsLoader.h"
#include <QString>
#include <vector>
#include <memory>


struct CompositionsFinder
{
	std::vector<QString> Find(std::vector<NoteTag> const& notes);
	CompositionsFinder(std::shared_ptr<CompositionsLoader> loader);
};
