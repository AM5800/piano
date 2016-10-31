#include <sstream>
#include <ostream>

struct Rational
{
	Rational(int nominator) : myNominator(nominator), myDenominator(1)
	{
		Normalize();
	}

	Rational(int nominator, int denominator) : myNominator(nominator), myDenominator(denominator)
	{
		Normalize();
	}

	Rational& operator+=(Rational const& other)
	{
		int gcd = ComputeGcd(other.myDenominator, myDenominator);
		int otherNom = other.myNominator;
		int lcm = myNominator / gcd * otherNom;

		otherNom *= lcm / other.myDenominator;
		myNominator *= lcm / myDenominator;

		myNominator += otherNom;
		myDenominator = lcm;

		Normalize();

		return *this;
	}

	int GetNominator() const
	{
		return myNominator;
	}

	int GetDenominator() const
	{
		return myDenominator;
	}

	Rational operator-() const {
		return Rational(-myNominator, myDenominator);
	}

private:
	int myNominator;
	int myDenominator;

	int ComputeGcd(int a, int b) const
	{
		if (b == 0) return a;
		return ComputeGcd(b, a % b);
	}

	void Normalize()
	{
		int gcd = ComputeGcd(myNominator, myDenominator);
		myNominator /= gcd;
		myDenominator /= gcd;

		if (myDenominator < 0)
		{
			myNominator = -myNominator;
			myDenominator = -myDenominator;
		}
	}
};

inline Rational operator+(Rational const& a, Rational const& b)
{
	Rational result = a;
	result += b;
	return result;
}

inline Rational operator-(Rational const& a, Rational const& b)
{
	Rational result = a;
	result += -b;
	return result;
}

inline bool operator==(Rational const& a, Rational const& b)
{
	return a.GetNominator() == b.GetNominator() && a.GetDenominator() == b.GetDenominator();
}

inline bool operator!=(Rational const& a, Rational const& b)
{
	return !(a == b);
}

inline std::ostream& operator<<(std::ostream& stream, Rational const& r)
{
	stream << r.GetNominator() << "/" << r.GetDenominator();
	return stream;
}

inline std::string to_string(Rational const& rational)
{
	std::stringstream ss;
	ss << rational;
	return ss.str();
}
