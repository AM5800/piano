#include "CompositionsLoader.h"
#include "Composition.h"

CompositionsLoader::CompositionsLoader()
{
	auto timeSignature = Rational(3, 8);
	CompositionBuilder builder("For Elise excerpt", timeSignature, TonalityEnum::Am);
	auto composition = builder
		.StartBar()
		.Pause(timeSignature - Rational(2, 16))
		.Note(E, Rational(1, 16))
		.Note(D, Rational(1, 16), NoteModifier::Legato | NoteModifier::Sharp)
		.StartBar()
		.Note(E, Rational(1, 16), NoteModifier::Legato)
		.Note(D, Rational(1, 16), NoteModifier::Legato | NoteModifier::Sharp)
		.Note(E, Rational(1, 16), NoteModifier::Legato)
		.Note(B, Rational(1, 16), NoteModifier::Legato)
		.Note(D, Rational(1, 16), NoteModifier::Legato)
		.Note(C, Rational(1, 16), NoteModifier::Legato)
		.Note(A, Rational(1, 16), NoteModifier::Legato)
		.End();
	myCompositions.push_back(composition);
}
