using Microsoft.VisualBasic;
using System.Runtime.CompilerServices;
using System.Runtime.Serialization.Formatters.Binary;

namespace lab7 // Note: actual namespace depends on the project name.
{
    internal class Program
    {
        static void Main(string[] args)
        {
            AppContext.SetSwitch("System.Runtime.Serialization.EnableUnsafeBinaryFormatterSerialization", true);
            /*
            if(args.Length == 0 || !Directory.Exists(args[0])) 
            {
                Console.WriteLine("Zly parametr");
                return;
            }
            */

            string directoryPath = "C:\\Lab3sem\\Platformy-technologiczne\\lab7\\ConsoleApp1";//args[0];
            DirectoryInfo directoryInfo = new DirectoryInfo(directoryPath);
            //DisplayDirectoryContent(directoryInfo);
            //DateTime oldestFileDate = GetOldestFileDate(directoryInfo);
            //Console.WriteLine($"\nNajstarszy plik: {oldestFileDate}");
            var sortedCollection = LoadToSortedCollection(directoryInfo);
            DisplaySortedCollection(sortedCollection);

            SerializeAndDesarializeCollection(sortedCollection);

        }

        static void DisplayDirectoryContent(DirectoryInfo directoryInfo, int level = 0) 
        {
            string indentation = string.Empty;
            for (int i = 0; i < level; i++)
                indentation += "\t";

            foreach (FileInfo file in directoryInfo.GetFiles())
                Console.WriteLine($"{indentation}{file.Name} {file.Length} bytes {GetFileAttributes(file)}");

            foreach(DirectoryInfo directory in directoryInfo.GetDirectories())
            {
                Console.WriteLine($"{indentation}{directory.Name} ({directory.GetFiles("*.*", SearchOption.AllDirectories).Length}) {GetFileAttributes(directory)}");
                DisplayDirectoryContent(directory, level + 1);
            }
        }

        static string GetFileAttributes(FileSystemInfo file)
        {
            string attributes = string.Empty;
            if ((file.Attributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
                attributes += "r";
            else attributes += "-";
            if((file.Attributes & FileAttributes.Archive) == FileAttributes.Archive)
                attributes += "a";
            else attributes += "-";
            if((file.Attributes & FileAttributes.Hidden) == FileAttributes.Hidden) 
                attributes += "h";
            else 
                attributes += "-";
            if((file.Attributes & FileAttributes.System) == FileAttributes.System)
                attributes += "s";
            else
                attributes += "-";
            return attributes;
        }

        static DateTime GetOldestFileDate(DirectoryInfo directory)
        {
            return directory.GetFiles("*.*", SearchOption.AllDirectories)
                .Select(file => file.LastWriteTime)
                .OrderBy(date =>date).FirstOrDefault();
        }

        static void DisplaySortedCollection(SortedDictionary<string, long> collection)
        {
            foreach(KeyValuePair<string, long> kvp in collection)
                Console.WriteLine($"{kvp.Key} -> {kvp.Value}");
        }

        static SortedDictionary<string, long> LoadToSortedCollection(DirectoryInfo directoryInfo)
        {
            FilesComparer comparer = new FilesComparer();
            SortedDictionary<string, long> sortedCollection = new SortedDictionary<string, long>(comparer);
            foreach(FileInfo file in directoryInfo.GetFiles())
                sortedCollection.Add(file.Name, file.Length);

            foreach (DirectoryInfo directory in directoryInfo.GetDirectories())
                sortedCollection.Add(directory.Name, directory.GetFiles().Length);

            return sortedCollection;
        }


        [Serializable]
        class FilesComparer : IComparer<string>
        {
            public int Compare(string? x, string? y)
            {
                return String.Compare(x, y, StringComparison.Ordinal);
            }
        }

        static void SerializeAndDesarializeCollection(SortedDictionary<string, long> collection)
        {
            #pragma warning disable SYSLIB0011
            BinaryFormatter binaryFormatter = new BinaryFormatter();
            using (FileStream fileStream = new FileStream("collection.bin", FileMode.Create))
                binaryFormatter.Serialize(fileStream, collection);

            using (FileStream fileStream = new FileStream("collection.bin", FileMode.Open))
            {
                SortedDictionary<string, long> deserializedCollection = (SortedDictionary<string, long>)binaryFormatter.Deserialize(fileStream);
                Console.WriteLine("\nDeserialized collection");
                DisplaySortedCollection(deserializedCollection);
            }
            #pragma warning restore SYSLIB0011

        }
    }
}