#pragma once

#include "../common/note.h"
#include "CompositionsLoader.h"
#include <vector>
#include <memory>

struct CompositionsFinder
{
	std::vector<Composition> Find(std::vector<NoteTag> const& notes);
	CompositionsFinder(std::shared_ptr<CompositionsLoader> loader);
};
