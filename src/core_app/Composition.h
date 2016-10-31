#pragma once

#include <vector>
#include <QString>
#include "../common/note.h"
#include "../common/Rational.h"
#include <numeric>

enum class NoteModifier
{
	None = 0,
	Legato = 1,
	Stacatto = 2,
	Sharp = 4,
	Flat = 8
};

struct Bar
{
	struct BarNote
	{
		NoteTag const Note;
		Rational const Duration;
		NoteModifier const Modifiers;

		BarNote(NoteTag note, Rational const& duration, NoteModifier modifiers)
			: Note(note),
			  Duration(duration),
			  Modifiers(modifiers)
		{
		}
	};

	std::vector<BarNote> const Notes;

	explicit Bar(std::vector<BarNote> const& notes)
		: Notes(notes)
	{
	}
};

enum class TonalityEnum
{
	C,
	Am
};

struct Composition
{
	QString const Title;
	Rational const TimeSignature;
	TonalityEnum const Tonality;

	Composition(QString const& title, Rational const& timeSignature, TonalityEnum tonality, std::vector<Bar> const& bars)
		: Title(title),
		  TimeSignature(timeSignature),
		  Tonality(tonality),
		  Bars(bars)
	{
	}

	std::vector<Bar> const Bars;
};


inline NoteModifier operator|(NoteModifier a, NoteModifier b)
{
	return static_cast<NoteModifier>(static_cast<int>(a) | static_cast<int>(b));
}

struct CompositionBuilder
{
	QString const Title;
	Rational const TimeSignature;
	TonalityEnum const Tonality;

	CompositionBuilder(QString const& title, Rational const& timeSignature, TonalityEnum tonality)
		: Title(title),
		  TimeSignature(timeSignature),
		  Tonality(tonality)
	{
	}

	CompositionBuilder& StartBar()
	{
		EndBar();
		return *this;
	}

	CompositionBuilder& Note(NoteTag noteTag, Rational const& duration, NoteModifier modifiers = NoteModifier::None)
	{
		myCurrentBar.push_back(Bar::BarNote(noteTag, duration, modifiers));
		return *this;
	}

	Composition End()
	{
		EndBar();

		return Composition(Title, TimeSignature, Tonality, myBars);
	}

	CompositionBuilder& Pause(Rational const& duration)
	{
		myCurrentBar.push_back(Bar::BarNote(NoteTag::Pause, duration, NoteModifier::None));
		return *this;
	}

private:
	std::vector<Bar> myBars;
	std::vector<Bar::BarNote> myCurrentBar;

	void EndBar()
	{
		if (myCurrentBar.size() == 0) return;

		auto totalDuration = std::accumulate(myCurrentBar.begin(), myCurrentBar.end(), Rational(0), [](Rational accumulator, Bar::BarNote const& note)
		                                     {
			                                     return note.Duration + accumulator;
		                                     });

		if (totalDuration != 1) throw std::exception(("Total duration: " + to_string(totalDuration)).c_str());

		myBars.push_back(Bar(myCurrentBar));
		myCurrentBar.clear();
	}
};
