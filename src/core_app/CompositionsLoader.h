#pragma once

#include <vector>
#include "Composition.h"

struct CompositionsLoader
{
	std::vector<Composition> GetCompositions() const
	{
		return myCompositions;
	}

	CompositionsLoader();
private:
	std::vector<Composition> myCompositions;
};