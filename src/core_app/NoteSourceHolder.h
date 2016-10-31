#ifndef NOTESOURCEFACTORYHELPER_H
#define NOTESOURCEFACTORYHELPER_H

#include <QString.h>
#include "..\common\inotesource.h"

struct NoteSourceHolder
{
	NoteSourceHolder(QString const& libPath);

	~NoteSourceHolder()
	{
		if (myFreeFunction) myFreeFunction(myInstance);
	}

	INoteSource* GetInstance() const
	{
		return myInstance;
	}

	operator INoteSource*() const
	{
		return GetInstance();
	}

	INoteSource* operator ->() const
	{
		return GetInstance();
	}

private:

	typedef INoteSource*(*CreateFunction)();
	typedef void (*FreeFunction)(INoteSource*);

	FreeFunction myFreeFunction;
	INoteSource* myInstance;
};

#endif // NOTESOURCEFACTORYHELPER_H
