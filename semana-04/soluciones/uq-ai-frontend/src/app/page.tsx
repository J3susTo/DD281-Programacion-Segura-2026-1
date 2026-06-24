import Navbar from "@/components/Navbar";
import Hero from "@/components/Hero";
import Services from "@/components/Services";
import Academy from "@/components/Academy";
import Lab from "@/components/Lab";
import ContactForm from "@/components/ContactForm";
import Footer from "@/components/Footer";

export default function Home() {
  return (
    <main className="min-h-screen bg-gray-950 text-white">
      <Navbar />
      <Hero />
      <Services />
      <Academy />
      <Lab />
      <ContactForm />
      <Footer />
    </main>
  );
}
