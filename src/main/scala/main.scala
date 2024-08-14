
import cats.*
import cats.implicits.*

import scala.math.BigInt.int2bigInt

val gcd: Int => Int => Int =
  x => y => x.gcd(y).intValue

val `(>)`: Int => Int => Boolean =
  x => y => x > y

extension [A,B](f: A => B)
  def `<$>`[F[_]:Functor](fa: F[A]): F[B] = fa.map(f)

def liftA2_v1[A,B,C,F[_]: Applicative](f: A => B => C)(fa: F[A],fb: F[B]): F[C] =
  fa.map(f) <*> fb

def liftA2_v2[A,B,C,F[_]: Applicative](f: A => B => C)(fa: F[A],fb: F[B]): F[C] =
  f `<$>` fa <*> fb

def liftA2_v3[A,B,C,F[_]: Applicative](f: A => B => C)(fa: F[A],fb: F[B]): F[C] =
  f.pure <*> fa <*> fb

val leapyear1: Int => Boolean =
  liftA2_v1(`(>)`)(gcd(80), gcd(50))

val leapyear2: Int => Boolean =
  liftA2_v2(`(>)`)(gcd(80), gcd(50))

val leapyear3: Int => Boolean =
  liftA2_v3(`(>)`)(gcd(80), gcd(50))

@main
def main(): Unit = {

  for
    leapYear <- List(leapyear1, leapyear2, leapyear3)
    _ = assert(List.range(2000,2025).filter(leapYear) == List(2000, 2004, 2008, 2012, 2016, 2020, 2024))
    _ = assert(List(1600, 1700, 1800, 1900, 2000).filter(leapYear) == List(1600, 2000))
  yield ()

}

